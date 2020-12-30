package sl.com.eightdigitz.country_picker.data.datasource

import sl.com.eightdigitz.country_picker.domain.model.DCountry

interface CountriesDataSource {
    fun getCountries(): MutableList<DCountry>
}