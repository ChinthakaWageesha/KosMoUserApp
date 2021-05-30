package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DUser

interface ExploreRepository {

    fun getExploreFeed(preference:  Array<String>?): Single<ListResponse<DUser>>
}