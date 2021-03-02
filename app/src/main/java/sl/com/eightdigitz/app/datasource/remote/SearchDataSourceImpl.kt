package sl.com.eightdigitz.app.datasource.remote

import android.content.SharedPreferences
import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.SearchDataSource
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.client.apis.PreferencesApi
import sl.com.eightdigitz.client.apis.SearchHistoryApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.presentation.extensions.getIdToken
import sl.com.eightdigitz.presentation.extensions.getUserString
import sl.com.eightdigitz.presentation.extensions.jsonStringMapTo

class SearchDataSourceImpl(
    private val mSharedPreferences: SharedPreferences,
    private val preferencesApi: PreferencesApi,
    private val searchHistoryApi: SearchHistoryApi
) : SearchDataSource {

    override fun getPreferenceCategories(): Single<ListResponse<DPreference>> =
        preferencesApi.getPreferences(
            language = "en"
        ).map {
            it.mapToDomain()
        }

    override fun getRecentSearchedTalents(): Single<ListResponse<DUser>> =
        searchHistoryApi.getRecentSearchedTalents(
            userId = mSharedPreferences.getUserString()?.jsonStringMapTo<PUser>()?.id!!
        ).map {
            it.mapToDomain()
        }

    override fun getTalentsByPreference(preferenceId: String): Single<ListResponse<DUser>> =
        preferencesApi.getTalentsByPreference(
            idToken = mSharedPreferences.getIdToken()!!,
            preferenceId = preferenceId
        ).map {
            it.mapToDomain()
        }

    override fun logPreferenceSearch(logSearchRequest: LogSearchRequest): Single<DUserSearch> =
        searchHistoryApi.logSearch(
            idToken = mSharedPreferences.getIdToken()!!,
            logSearchRequest = logSearchRequest
        ).map {
            it.data?.mapToDomain()
        }

    override fun removeRecentViewedProfile(
        ownerId: String,
        searchType: String
    ): Single<DUserSearch> =
        searchHistoryApi.removeRecentlyViewedProfile(
            idToken = mSharedPreferences.getIdToken()!!,
            ownerId = ownerId,
            userId = mSharedPreferences.getUserString()?.jsonStringMapTo<PUser>()?.id!!,
            searchType = searchType
        ).map {
            it.data?.mapToDomain()
        }
}