package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser

interface SearchDataSource {

    fun getPreferenceCategories(): Single<ListResponse<DPreference>>

    fun getRecentSearchedTalents(): Single<ListResponse<DUser>>

    fun getTalentsByPreference(
        preferenceId: String
    ): Single<ListResponse<DUser>>

    fun logPreferenceSearch(
        logSearchRequest: LogSearchRequest
    ): Single<DUserSearch>

    fun removeRecentViewedProfile(
        ownerId: String,
        searchType: String
    ): Single<DUserSearch>




}