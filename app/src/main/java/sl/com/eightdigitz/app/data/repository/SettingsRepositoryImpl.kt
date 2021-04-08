package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.SettingsDataSource
import sl.com.eightdigitz.app.domain.repository.SettingsRepository
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference

class SettingsRepositoryImpl constructor(
    private val settingsDataSource: SettingsDataSource
) : SettingsRepository {

    override fun logout(): Single<ListResponse<DPreference>> =
        settingsDataSource.logout()
}