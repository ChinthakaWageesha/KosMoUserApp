package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_language_selection.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.di.injectFeature
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.LanguageType
import sl.com.eightdigitz.presentation.extensions.showToast

class LanguageSelection : BaseActivity(), View.OnClickListener {

    private var language: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection)
        injectFeature()
        init()
    }

    private fun init() {
        btn_english.isChecked = true
        rg_select_language_2.clearCheck()

        rg_select_language_1.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                fun2();
            }
        }

        rg_select_language_2.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                fun1();
            }
        }
        btn_continue.setOnClickListener(this)
    }

    private fun fun1() {
        rg_select_language_1.setOnCheckedChangeListener(null)
        rg_select_language_1.clearCheck()
        rg_select_language_1.setOnCheckedChangeListener { _, _ -> fun2() }
    }

    private fun fun2() {
        rg_select_language_2.setOnCheckedChangeListener(null)
        rg_select_language_2.clearCheck()
        rg_select_language_2.setOnCheckedChangeListener { _, _ -> fun1() }
    }


    fun authSuccess() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_continue -> {
                if (btn_english.isChecked) {
                    language = LanguageType.ENGLISH
                }
                if (btn_sinhala.isChecked) {
                    language = LanguageType.SINHALA
                }
                if (btn_tamil.isChecked) {
                    language = LanguageType.TAMIL
                }
                if (btn_hindi.isChecked) {
                    language = LanguageType.HINDI
                }

                GetStarted.startActivity(this, language!!)
            }
        }
    }
}