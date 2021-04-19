package sl.com.eightdigitz.app.datasource.remote

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.ExploreDataSource
import sl.com.eightdigitz.client.apis.ExploreApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain

class ExploreDataSourceImpl(
    private val exploreApi: ExploreApi
) : ExploreDataSource {

    override fun getTalents(): Single<ListResponse<DUser>> =
        exploreApi.getTalents(
            role = "talent"
        ).map {
            it.mapToDomain()
        }
}