package sl.com.eightdigitz.chat.data.repository

import sl.com.eightdigitz.chat.data.datasource.FileUploadDataSource
import sl.com.eightdigitz.chat.domain.model.DMessageFile
import sl.com.eightdigitz.chat.domain.repository.FileUploadRepository
import io.reactivex.Single
import okhttp3.MultipartBody

class FileUploadRepositoryImpl constructor(private val fileUploadDataSource: FileUploadDataSource) : FileUploadRepository{
    override fun uploadFile(file: MultipartBody.Part, thumbnail: MultipartBody.Part?): Single<DMessageFile>  =
        fileUploadDataSource.uploadFile(file, thumbnail).doOnSuccess{

        }
}