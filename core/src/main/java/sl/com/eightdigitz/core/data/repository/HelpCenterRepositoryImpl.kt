package sl.com.eightdigitz.core.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.data.datasource.HelpCenterDataSource
import sl.com.eightdigitz.core.domain.repository.HelpCenterRepository
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.country_picker.domain.model.DCountry

class HelpCenterRepositoryImpl constructor(
    private val helpCenterDataSource: HelpCenterDataSource
) : HelpCenterRepository {

    override fun getCountries(): MutableList<DCountry> =
        helpCenterDataSource.getCountries()

    override fun getAppInfo(country: String, language: String): Single<DAppInfo> =
        helpCenterDataSource.getAppInfo(country, language)
}