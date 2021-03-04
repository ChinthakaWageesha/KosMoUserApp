package sl.com.eightdigitz.core.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.country_picker.domain.model.DCountry

interface HelpCenterDataSource {

    fun getCountries(): MutableList<DCountry>

    fun getAppInfo(country: String, language: String): Single<DAppInfo>
}