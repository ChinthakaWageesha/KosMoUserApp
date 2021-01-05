package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_choose_language.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.presentation.LanguageType
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.Callback
import sl.com.eightdigitz.presentation.extensions.showConfirm

class ChooseLanguage : BaseFragment(), View.OnClickListener {

    private var language: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_language, container, false)
    }

    override fun onViewCreated() {
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
                (requireActivity() as AuthActivity).language = language
                (requireActivity() as AuthActivity).setGetStarted()
            }
        }
    }

    companion object{
        const val TAG = "choose_language"

        fun newInstance(): Fragment {
            return ChooseLanguage()
        }
    }
}