package sl.com.eightdigitz.chat.media

interface ProgressUpdateListener {
    fun onProgressUpdate(progress: Long, bufferedProgress: Long, duration: Long)
}