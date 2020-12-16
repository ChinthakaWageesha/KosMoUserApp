package sl.com.eightdigitz.authentication.utils.pdf

import java.io.File

interface Download {

    /**
     * @param url
     * @param customName custom name of the file
     */
    fun download(url: String, customName: String)

    fun download(url: String)

    interface Listener {
        fun onSuccess(url: String, destinationPath: File)

        fun onFailure(e: Exception)

        fun onProgressUpdate(progress: Int, total: Int)
    }
}
