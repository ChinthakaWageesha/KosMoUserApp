package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.models.PreferenceListResponse
import sl.com.eightdigitz.client.models.UserListResponse

@JvmSuppressWildcards
interface PreferencesApi {

    @GET("preferences")
    fun getPreferences(
        @retrofit2.http.Query("language") language: String
    ): Single<PreferenceListResponse>

    @GET("user/get-profile-list-by-preference")
    fun getTalentsByPreference(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Query("preference-uuid") preferenceId: String
    ): Single<UserListResponse>
}