package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.models.PreferenceListResponse

@JvmSuppressWildcards
interface PreferencesApi {

    @GET("preferences")
    fun getPreferences(
        @retrofit2.http.Query("language") language: String
    ): Single<PreferenceListResponse>
}