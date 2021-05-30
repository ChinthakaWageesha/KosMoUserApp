package sl.com.eightdigitz.core.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_terms_of_use.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DAppInfo
import sl.com.eightdigitz.core.model.domain.DLanguage
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.setHelpCenterActionBar
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.withNetwork

class TermsOfUse : BaseActivity() {

    private val vmHelp by viewModel<HelpCenterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_use)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setHelpCenterActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_terms_of_use),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        setLanguageSpinner()
        wv_tnc.setBackgroundColor(Color.TRANSPARENT)
        vmHelp.liveDataTermsOfUse.observe(this, Observer { observerGetTermsOfUse(it) })
    }

    private fun getTermsOfUse(language: String){
        withNetwork({
           vmHelp.getTermsOfUse(language)
        },{
            showAlert(message = Msg.INTERNET_ISSUE)
        })
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


        if (sp_language_tnc != null) {
            val adapter = ArrayAdapter(
                this,
                sl.com.eightdigitz.presentation.R.layout.item_spinner,
                sl.com.eightdigitz.presentation.R.id.tv_spinner_category_name,
                languages
            )

            sp_language_tnc.adapter = adapter

            sp_language_tnc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int,
                    id: Long
                ) {
                    getTermsOfUse(languageList[position].languageCode!!)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

    private fun observerGetTermsOfUse(resource: Resource<DAppInfo>){
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    loadDataWebView(it.data?.description!!)
                    tv_tnc_last_update.text = it.data?.updatedAt!!.split("T")[0]
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    loadDataWebView(Msg.ERROR_APP_INFO)
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadDataWebView(data: String){
        wv_tnc.settings.javaScriptEnabled = true
        wv_tnc.loadData(data, "text/html; charset=utf-8", "UTF-8")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}