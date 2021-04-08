package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.SearchDataSource
import sl.com.eightdigitz.app.domain.repository.SearchRepository
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser

class SearchRepositoryImpl constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override fun getPreferenceCategories(): Single<ListResponse<DPreference>> =
        searchDataSource.getPreferenceCategories()

    override fun getRecentSearchedTalents(): Single<ListResponse<DUser>> =
        searchDataSource.getRecentSearchedTalents()

    override fun getTalentsByPreference(
        preferenceId: String,
        searchKey: String?
    ): Single<ListResponse<DUser>> =
        searchDataSource.getTalentsByPreference(
            preferenceId = preferenceId,
            searchKey = searchKey
        )

    override fun logSearch(logSearchRequest: LogSearchRequest): Single<DUserSearch> =
        searchDataSource.logSearch(
            logSearchRequest = logSearchRequest
        )

    override fun removeRecentViewedProfile(
        ownerId: String,
        searchType: String
    ): Single<DUserSearch> =
        searchDataSource.removeRecentViewedProfile(
            ownerId = ownerId,
            searchType = searchType
        )

    override fun getTalents(searchKey: String?): Single<ListResponse<DUser>> =
        searchDataSource.getTalents(searchKey)

}