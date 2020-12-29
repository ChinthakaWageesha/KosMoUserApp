package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_language_selection.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.di.injectFeature
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.navigation.features.ResultCode
import sl.com.eightdigitz.presentation.extensions.startActivity

class LanguageSelection : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)
        injectFeature()
        init()
    }

    private fun init() {
        btn_sinhala.setOnClickListener(this)
        btn_tamil.setOnClickListener(this)
        btn_english.setOnClickListener(this)
    }


    fun authSuccess() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sinhala -> {
                startActivity<GetStarted>()
            }
            R.id.btn_tamil -> {
                startActivity<GetStarted>()
            }
            R.id.btn_english -> {
                startActivity<GetStarted>()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}