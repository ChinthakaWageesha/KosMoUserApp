package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import sl.com.eightdigitz.client.apiSupports.models.FirebaseToken
import sl.com.eightdigitz.client.apiSupports.responses.FirebaseTokenResponse
import sl.com.eightdigitz.client.apiSupports.responses.NotificationResponse
import sl.com.eightdigitz.client.apiSupports.responses.SuccessResponse

@JvmSuppressWildcards
interface NotificationApi {

    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    @POST("fcm-notification/welcome/{user-id}")
    fun requestWelcomeNotification(
        @retrofit2.http.Path("user-id") userId: String
    ): Single<SuccessResponse>


    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    @GET("fcm-notification/all-success")
    fun notificationsGet(
        @retrofit2.http.Query("user-id") userId: String
    ): Single<NotificationResponse>

}