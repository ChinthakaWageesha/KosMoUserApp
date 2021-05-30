package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.apiSupports.responses.AppInfoResponse

@JvmSuppressWildcards
interface HelpCenterApi {

    @GET("privacy-policy")
    fun getPrivacyPolicy(
        @retrofit2.http.Query("country") country: String,
        @retrofit2.http.Query("language") language: String
    ): Single<AppInfoResponse>

    @GET("terms-and-condition")
    fun getTermsOfUse(
        @retrofit2.http.Query("language") language: String
    ): Single<AppInfoResponse>
}