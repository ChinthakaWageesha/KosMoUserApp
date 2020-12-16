package sl.com.eightdigitz.app.presentation.utill

import android.content.Context
import androidx.annotation.NonNull
import java.io.File

class FileUtil {

    companion object {

        @JvmStatic
        fun getParentFile(@NonNull context: Context): File {
            val externalSaveDir =
                context.getExternalFilesDir("userApp") // context.getExternalCacheDir();
            return externalSaveDir ?: context.cacheDir
        }
    }
}
