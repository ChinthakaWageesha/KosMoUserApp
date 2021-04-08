package sl.com.eightdigitz.app.datasource.remote

import android.content.SharedPreferences
import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.SettingsDataSource
import sl.com.eightdigitz.client.apis.PreferencesApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.presentation.extensions.cleaAll

class SettingsDataSourceImpl(
    private val mSharedPreferences: SharedPreferences,
    private val preferencesApi: PreferencesApi
) : SettingsDataSource{

    override fun logout(): Single<ListResponse<DPreference>> =
        preferencesApi.getPreferences(
            language = "en"
        ).map {
            mSharedPreferences.cleaAll()
            it.mapToDomain()
        }
}