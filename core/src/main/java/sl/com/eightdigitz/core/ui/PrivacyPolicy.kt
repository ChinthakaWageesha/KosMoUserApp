package sl.com.eightdigitz.core.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_privacy_policy.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.core.model.domain.DLanguage
import sl.com.eightdigitz.country_picker.domain.model.DCountry
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.setHelpCenterActionBar
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.withNetwork

class PrivacyPolicy : BaseActivity() {

    private val vmHelp by viewModel<HelpCenterViewModel>()
    private var country = ""
    private var language = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setHelpCenterActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_privacy_policy),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        vmHelp.getCountries()
        setLanguageSpinner()
        wv_privacy_policy.setBackgroundColor(Color.TRANSPARENT)
        vmHelp.liveDataCountries.observe(this, Observer { observerGetCountries(it) })
        vmHelp.liveDataPrivacyPolicy.observe(this, Observer { observerGetPrivacyPolicy(it) })
    }

    @SuppressLint("MissingPermission")
    private fun getPrivacyPolicy() {
        withNetwork({
            vmHelp.getPrivacyPolicy(country, language)
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun observerGetCountries(resource: Resource<MutableList<DCountry>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setCountrySpinner(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerGetPrivacyPolicy(resource: Resource<DAppInfo>){
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    loadDataWebView(it.data?.description!!)
                    tv_privacy_policy_last_update.text = it.data?.updatedAt!!.split("T")[0]
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    loadDataWebView(Msg.ERROR_APP_INFO)
                }
            }
        }
    }

    private fun setCountrySpinner(countryList: MutableList<DCountry>) {

        val countries: ArrayList<String> = ArrayList()
        for (i in countryList.indices) {
            countries.add(countryList[i].name.toString())
        }

        if (sp_country != null) {
            val adapter = ArrayAdapter(
                this,
                sl.com.eightdigitz.presentation.R.layout.item_spinner,
                sl.com.eightdigitz.presentation.R.id.tv_spinner_category_name,
                countries
            )

            sp_country.adapter = adapter
            sp_country.setSelection(202)

            sp_country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    country = countryList[position].code!!
                    getPrivacyPolicy()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {

                }
            }
        }
    }

    private fun setLanguageSpinner() {
        val languageList: MutableList<DLanguage> = mutableListOf()
        val languages: ArrayList<String> = ArrayList()
        val langOne = DLanguage()
        langOne.language = "English"
        langOne.languageCode = "en"

        val langTwo = DLanguage()
        langTwo.language = "Sinhala"
        langTwo.languageCode = "si"

        val langThree = DLanguage()
        langThree.language = "Tamil"
        langThree.languageCode = "ta"

        val langFour = DLanguage()
        langFour.language = "Hindi"
        langFour.languageCode = "hi"

        languageList.add(langOne)
        languageList.add(langTwo)
        languageList.add(langThree)
        languageList.add(langFour)

        for (i in languageList.indices) {
            languages.add(languageList[i].language.toString())
        }


        if (sp_language != null) {
            val adapter = ArrayAdapter(
                this,
                sl.com.eightdigitz.presentation.R.layout.item_spinner,
                sl.com.eightdigitz.presentation.R.id.tv_spinner_category_name,
                languages
            )

            sp_language.adapter = adapter

            sp_language.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int,
                    id: Long
                ) {
                    language = languageList[position].languageCode!!
                    getPrivacyPolicy()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadDataWebView(data: String){
        wv_privacy_policy.settings.javaScriptEnabled = true
        wv_privacy_policy.loadData(data, "text/html; charset=utf-8", "UTF-8")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
