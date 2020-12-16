package sl.com.eightdigitz.chat.domain.repository



import sl.com.eightdigitz.chat.domain.model.DMessageFile
import io.reactivex.Single
import okhttp3.MultipartBody

interface FileUploadRepository {
    fun uploadFile(file: MultipartBody.Part, thumbnail: MultipartBody.Part?): Single<DMessageFile>
}