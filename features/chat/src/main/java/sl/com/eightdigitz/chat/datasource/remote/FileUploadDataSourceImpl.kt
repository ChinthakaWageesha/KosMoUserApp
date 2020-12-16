package sl.com.eightdigitz.chat.datasource.remote

import sl.com.eightdigitz.chat.data.datasource.FileUploadDataSource
import sl.com.eightdigitz.chat.domain.model.DMessageFile
import sl.com.eightdigitz.chat.domain.model.mapToDomain
import sl.com.eightdigitz.client.apis.ChatApi
import io.reactivex.Single
import okhttp3.MultipartBody

class FileUploadDataSourceImpl (private val chatApi: ChatApi) : FileUploadDataSource{
    override fun uploadFile(file: MultipartBody.Part, thumbnail: MultipartBody.Part?): Single<DMessageFile> =
        chatApi.chatFilesPost(file, thumbnail).map {
            it.payload?.mapToDomain()
        }
}