package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUser

interface ExploreDataSource {

    fun getExploreFeed(preference:  Array<String>?): Single<ListResponse<DUser>>

}