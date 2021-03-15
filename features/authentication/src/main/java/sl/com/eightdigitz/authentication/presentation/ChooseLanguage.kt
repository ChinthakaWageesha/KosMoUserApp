package sl.com.eightdigitz.authentication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_choose_language.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.contact.ContactSupport
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.presentation.LanguageType
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.*

class ChooseLanguage : BaseFragment(), View.OnClickListener {

    private var language: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_choose_language, container, false)
    }

    override fun onViewCreated() {
        setToolbar()
        init()
    }

    private fun setToolbar() {
        (requireActivity() as AuthActivity).supportActionBar?.setActionBar(
            context!!,
            "",
            isHomeUpEnables = false
        )
    }

    private fun init() {
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
        tv_contact_us.setOnClickListener(this)
    }

    private fun fun1() {
        rg_select_language_1.setOnCheckedChangeListener(null)
        rg_select_language_1.clearCheck()
        rg_select_language_1.setOnCheckedChangeListener { _, _ -> fun2() }
        btn_continue.setEnable()
    }

    private fun fun2() {
        rg_select_language_2.setOnCheckedChangeListener(null)
        rg_select_language_2.clearCheck()
        rg_select_language_2.setOnCheckedChangeListener { _, _ -> fun1() }
        btn_continue.setEnable()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_contact_us -> context?.startActivity<ContactSupport>()
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
                (requireActivity() as AuthActivity).setGetOTP()
            }
        }
    }

    override fun onResume() {
        hideKeyboard()
        setBackground(sl.com.eightdigitz.presentation.R.drawable.bg_choose_language)
        super.onResume()
    }

    companion object {
        const val TAG = "choose_language"

        fun newInstance(): Fragment {
            return ChooseLanguage()
        }
    }
}