package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.SearchDataSource
import sl.com.eightdigitz.app.domain.repository.SearchRepository
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser

class SearchRepositoryImpl constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override fun getPreferenceCategories(): Single<ListResponse<DPreference>> =
        searchDataSource.getPreferenceCategories()

    override fun getRecentSearchedTalents(userId: String): Single<ListResponse<DUser>> =
        searchDataSource.getRecentSearchedTalents(userId)

}