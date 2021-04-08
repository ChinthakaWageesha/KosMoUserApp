package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference

interface SettingsRepository {

    fun logout(): Single<ListResponse<DPreference>>
}