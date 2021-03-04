package sl.com.eightdigitz.core.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.country_picker.domain.model.DCountry

interface HelpCenterRepository {

    fun getCountries(): MutableList<DCountry>

    fun getAppInfo(country: String, language: String): Single<DAppInfo>
}