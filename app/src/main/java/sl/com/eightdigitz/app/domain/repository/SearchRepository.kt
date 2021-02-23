package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser

interface SearchRepository {

    fun getPreferenceCategories() : Single<ListResponse<DPreference>>

    fun getRecentSearchedTalents(userId: String) : Single<ListResponse<DUser>>
}