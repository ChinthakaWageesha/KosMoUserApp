package sl.com.eightdigitz.core.domain.usecase

import sl.com.eightdigitz.core.domain.repository.HelpCenterRepository
import sl.com.eightdigitz.country_picker.domain.model.DCountry

class HelpCenterUseCase(private val helpCenterRepository: HelpCenterRepository) {

    fun getCountries(): MutableList<DCountry> = helpCenterRepository.getCountries()

    fun getAppInfo(country: String, language: String) = helpCenterRepository.getAppInfo(
        country = country,
        language = language
    )
}