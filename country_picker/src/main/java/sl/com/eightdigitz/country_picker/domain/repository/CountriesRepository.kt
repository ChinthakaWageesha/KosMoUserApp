package sl.com.eightdigitz.country_picker.domain.repository

import sl.com.eightdigitz.country_picker.domain.model.DCountry

interface CountriesRepository {
    fun getCountries(): MutableList<DCountry>
}