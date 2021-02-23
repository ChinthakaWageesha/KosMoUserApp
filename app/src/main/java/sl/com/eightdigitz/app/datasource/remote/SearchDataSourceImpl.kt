package sl.com.eightdigitz.app.datasource.remote

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.SearchDataSource
import sl.com.eightdigitz.client.apis.PreferencesApi
import sl.com.eightdigitz.client.apis.SearchHistoryApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain

class SearchDataSourceImpl(
    private val preferencesApi: PreferencesApi,
    private val searchHistoryApi: SearchHistoryApi
) : SearchDataSource {

    override fun getPreferenceCategories(): Single<ListResponse<DPreference>> =
        preferencesApi.getPreferences(
            language = "en"
        ).map {
            it.mapToDomain()
        }

    override fun getRecentSearchedTalents(userId: String): Single<ListResponse<DUser>> =
        searchHistoryApi.getRecentSearchedTalents(userId).map {
            it.mapToDomain()
        }

}