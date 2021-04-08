package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference

interface SettingsDataSource {

    fun logout(): Single<ListResponse<DPreference>>

}