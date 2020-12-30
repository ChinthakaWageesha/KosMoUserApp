package sl.com.eightdigitz.country_picker.domain.usecase

import sl.com.eightdigitz.country_picker.domain.model.DCountry
import sl.com.eightdigitz.country_picker.domain.repository.CountriesRepository

class CountriesUseCase(private val mCountriesRepository: CountriesRepository) {

    fun getCountries(): MutableList<DCountry> = mCountriesRepository.getCountries()
}