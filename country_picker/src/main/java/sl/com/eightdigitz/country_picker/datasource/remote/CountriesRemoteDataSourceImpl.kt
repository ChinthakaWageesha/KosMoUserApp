package sl.com.eightdigitz.country_picker.datasource.remote

import android.content.Context
import android.util.Base64
import sl.com.eightdigitz.country_picker.data.datasource.CountriesDataSource
import com.google.gson.Gson
import sl.com.eightdigitz.country_picker.R
import sl.com.eightdigitz.country_picker.domain.model.DCountry
import java.nio.charset.Charset

class CountriesRemoteDataSourceImpl(private val mContext: Context) : CountriesDataSource {
    override fun getCountries(): MutableList<DCountry> {
        val base64 = mContext.resources.getString(R.string.countries_code)
        val decodedJson = Base64.decode(base64, Base64.DEFAULT).toString(Charset.defaultCharset())
        return Gson().fromJson(decodedJson, Array<DCountry>::class.java).toMutableList()
    }
}