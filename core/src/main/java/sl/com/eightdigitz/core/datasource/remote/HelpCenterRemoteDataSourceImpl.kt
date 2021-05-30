package sl.com.eightdigitz.core.datasource.remote

import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import io.reactivex.Single
import sl.com.eightdigitz.client.apis.HelpCenterApi
import sl.com.eightdigitz.core.data.datasource.HelpCenterDataSource
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.country_picker.R
import sl.com.eightdigitz.country_picker.domain.model.DCountry
import java.nio.charset.Charset

class HelpCenterRemoteDataSourceImpl(
    private val context: Context,
    private val helpCenterApi: HelpCenterApi
) : HelpCenterDataSource {

    override fun getCountries(): MutableList<DCountry> {
        val base64 = context.resources.getString(R.string.countries_code)
        val decodedJson = Base64.decode(base64, Base64.DEFAULT).toString(Charset.defaultCharset())
        return Gson().fromJson(decodedJson, Array<DCountry>::class.java).toMutableList()
    }

    override fun getPrivacyPolicy(country: String, language: String): Single<DAppInfo> =
        helpCenterApi.getPrivacyPolicy(
            country = country,
            language = language
        ).map {
            it.data?.mapToDomain()
        }

    override fun getTermsOfUse(language: String): Single<DAppInfo> =
        helpCenterApi.getTermsOfUse(
            language = language
        ).map {
            it.data?.mapToDomain()
        }

}