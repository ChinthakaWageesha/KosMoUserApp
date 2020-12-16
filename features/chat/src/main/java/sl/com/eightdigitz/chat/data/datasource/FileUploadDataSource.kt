package sl.com.eightdigitz.chat.data.datasource

import sl.com.eightdigitz.chat.domain.model.DMessageFile
import io.reactivex.Single
import okhttp3.MultipartBody

/**
 * Created by Chathura Wijesinghe on 3/4/19.
 * Elegantmedia
 * chaturaw.emedia@gmail.com
 */

interface FileUploadDataSource {

    fun uploadFile(file: MultipartBody.Part, thumbnail: MultipartBody.Part?): Single<DMessageFile>
}
