package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.ExploreDataSource
import sl.com.eightdigitz.app.domain.repository.ExploreRepository
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUser

class ExploreRepositoryImpl constructor(
    private val exploreDataSource: ExploreDataSource
) : ExploreRepository {

    override fun getTalents(): Single<ListResponse<DUser>> =
        exploreDataSource.getTalents()
}