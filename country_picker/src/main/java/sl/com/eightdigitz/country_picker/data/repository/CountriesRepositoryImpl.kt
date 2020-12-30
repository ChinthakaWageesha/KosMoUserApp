package sl.com.eightdigitz.country_picker.data.repository

import sl.com.eightdigitz.country_picker.data.datasource.CountriesDataSource
import sl.com.eightdigitz.country_picker.domain.model.DCountry
import sl.com.eightdigitz.country_picker.domain.repository.CountriesRepository

class CountriesRepositoryImpl(private val mCountriesDataSource: CountriesDataSource) :
    CountriesRepository {
    override fun getCountries(): MutableList<DCountry> = mCountriesDataSource.getCountries()
}