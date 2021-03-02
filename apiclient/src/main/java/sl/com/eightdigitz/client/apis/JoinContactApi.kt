package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST
import sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest
import sl.com.eightdigitz.client.apiSupports.responses.ContactUsResponse
import sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest
import sl.com.eightdigitz.client.apiSupports.responses.JoinUsResponse

@JvmSuppressWildcards
interface JoinContactApi {

    @POST("support")
    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    fun contactSupport(
        @retrofit2.http.Body contactTalentRequest: ContactUsRequest
    ): Single<ContactUsResponse>

    @POST("joining")
    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    fun joinUs(
        @retrofit2.http.Body joinTalentRequest: JoinUsRequest
    ): Single<JoinUsResponse>
}