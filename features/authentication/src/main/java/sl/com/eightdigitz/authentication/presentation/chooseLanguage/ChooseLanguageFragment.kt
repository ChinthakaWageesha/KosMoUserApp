package sl.com.eightdigitz.authentication.presentation.chooseLanguage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_choose_language.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.core.presentation.base.BaseFragment
import sl.com.eightdigitz.presentation.LanguageType


@SuppressLint("MissingPermission")
class ChooseLanguageFragment : BaseFragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_language, container, false);
    }

    override fun onViewCreated() {
        btn_sinhala.setOnClickListener(this)
        btn_tamil.setOnClickListener(this)
        btn_english.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sinhala -> {
                (requireActivity() as AuthActivity).languageType = LanguageType.SINHALA
                (requireActivity() as AuthActivity).setSignUp()
            }
            R.id.btn_tamil -> {
                (requireActivity() as AuthActivity).languageType = LanguageType.TAMIL
                (requireActivity() as AuthActivity).setSignUp()
            }
            R.id.btn_english -> {
                (requireActivity() as AuthActivity).languageType = LanguageType.ENGLISH
                (requireActivity() as AuthActivity).setSignUp()
            }
        }
    }

    companion object {
        const val TAG = "Select_User"

        fun newInstance(): Fragment {
            return ChooseLanguageFragment()
        }
    }
}
