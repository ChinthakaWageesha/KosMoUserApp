package sl.com.eightdigitz.authentication.utils.pdf

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class DownloadingImpl(
    private val context: Context,
    private val uiThread: Handler?,
    listener: Download.Listener
) : Download {
    private val listener = listener

    override fun download(url: String, fileName: String) {
        Thread(Runnable {
            var fileOutput: FileOutputStream? = null
            try {
                val file = File(context.filesDir, fileName)

                if (!isOnline(context)) {
                    if (file.exists()) {
                        notifySuccess(url, file)
                        return@Runnable
                    } else {
                        // in this case it will be empty so we need to delete
                        file.delete()
                        notifyFailure(Exception("No connection"))
                        return@Runnable
                    }
                }

                fileOutput = FileOutputStream(file)
                var urlConnection: HttpURLConnection? = null
                val urlObj = URL(url)
                urlConnection = urlObj.openConnection() as HttpURLConnection
                val totalSize = urlConnection.contentLength
                var downloadedSize = 0
                var counter = 0
                val buffer = ByteArray(BUFFER_LENGTH)
                var bufferLength: Int
                val inputStream = BufferedInputStream(urlConnection.inputStream)

                while ((inputStream.read(buffer)) > 0) {
                    bufferLength = inputStream.read(buffer)

                    fileOutput.write(buffer, 0, bufferLength)
                    downloadedSize += bufferLength
                    counter += bufferLength
                    if (listener != null && counter > NOTIFY_PERIOD) {
                        notifyProgress(downloadedSize, totalSize)
                        counter = 0
                    }
                }

                notifySuccess(url, file)
                urlConnection.disconnect()
                fileOutput.close()
            } catch (e: MalformedURLException) {
                notifyFailure(e)
            } catch (e: IOException) {
                notifyFailure(e)
            } finally {
                if (fileOutput != null) {
                    try {
                        fileOutput.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }).start()
    }

    /**
     * Will get a file name from url
     * @param url
     */
    override fun download(url: String) {
        download(url, url.substring(url.lastIndexOf('/') + 1))
    }

    private fun notifySuccess(url: String, destinationPath: File) {
        if (uiThread == null) {
            return
        }

        uiThread.post { listener.onSuccess(url, destinationPath) }
    }

    private fun notifyFailure(e: Exception) {
        if (uiThread == null) {
            return
        }

        uiThread.post { listener.onFailure(e) }
    }

    private fun notifyProgress(downloadedSize: Int, totalSize: Int) {
        if (uiThread == null) {
            return
        }

        uiThread.post { listener.onProgressUpdate(downloadedSize, totalSize) }
    }

    /**
     *
     * @param context
     * @return true if device online and false if offline
     */
    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    protected inner class OnDownloadingListener : Download.Listener {

        /**
         * This method will notify main thread that downloading is successful ended.
         * return
         * @param url (for example if person want to save it in a hashMap as a key)
         * and
         * @param file
         */
        override fun onSuccess(url: String, file: File) {
            /* Empty */
        }

        /**
         * this will return any error like no network or can't create new file and so on
         * @param e
         */
        override fun onFailure(e: Exception) {
            /* Empty */
        }

        /**
         * Wil return current
         * @param progress (current size)
         * and
         * @param total size left
         */
        override fun onProgressUpdate(progress: Int, total: Int) {
            /* Empty */
        }
    }

    companion object {

        private val BUFFER_LENGTH = 1024
        private val NOTIFY_PERIOD = 150 * 1024

        fun extractFileNameFromURL(url: String): String {
            return url.substring(url.lastIndexOf('/') + 1)
        }
    }
}
