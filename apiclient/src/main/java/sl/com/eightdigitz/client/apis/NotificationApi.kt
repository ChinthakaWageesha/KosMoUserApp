package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import sl.com.eightdigitz.client.apiSupports.responses.NotificationResponse

@JvmSuppressWildcards
interface NotificationApi {

    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    @GET("notifications")
    fun notificationsGet(
        @retrofit2.http.Query("page") page: Int?,
        @retrofit2.http.Query("per_page") perPage: Int?
    ): Single<NotificationResponse>

}