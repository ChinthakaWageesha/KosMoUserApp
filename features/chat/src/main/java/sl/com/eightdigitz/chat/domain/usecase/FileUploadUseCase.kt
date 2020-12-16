package sl.com.eightdigitz.chat.domain.usecase



import sl.com.eightdigitz.chat.domain.model.DMessageFile
import sl.com.eightdigitz.chat.domain.repository.FileUploadRepository
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FileUploadUseCase(private val fileUploadRepository: FileUploadRepository) {

//    Upload file use case  Supported file types: doc,pdf,docx,jpg,jpeg,png,gif,mpga,mp3,wav,mp4,mov,ogg,qt
    fun uploadFile(image: MultipartBody.Part, thumbnail: MultipartBody.Part?): Single<DMessageFile> =
        fileUploadRepository.uploadFile(image, thumbnail)
}