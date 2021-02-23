package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.POST
import sl.com.eightdigitz.client.models.UploadBigDataResponse

@JvmSuppressWildcards
interface MultimediaApi {

    @retrofit2.http.Multipart
    @POST("doc-upload/public")
    fun uploadAvatar(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Part bigData: MultipartBody.Part
    ): Single<UploadBigDataResponse>
}