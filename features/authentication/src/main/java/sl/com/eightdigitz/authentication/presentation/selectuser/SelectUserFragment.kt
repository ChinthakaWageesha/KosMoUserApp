package sl.com.eightdigitz.authentication.presentation.selectuser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.core.presentation.base.BaseFragment
import sl.com.eightdigitz.presentation.USER_TYPE
import sl.com.eightdigitz.presentation.extensions.setActionBar
import kotlinx.android.synthetic.main.fragment_selectuser.*


@SuppressLint("MissingPermission")
class SelectUserFragment : BaseFragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setActionBar(
            requireContext(), "Sign In", false, isCenterTitle = true
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selectuser, container, false);
    }

    override fun onViewCreated() {
        btn_business.setOnClickListener(this)
        btn_looking_service.setOnClickListener(this)
        tv_signin.setOnClickListener(this)
    }

    override fun progressBar(): View? = (activity as AuthActivity).progressBar()

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_business -> {
                (requireActivity() as AuthActivity).userType = USER_TYPE.TYPE_BUSINESS
                (requireActivity() as AuthActivity).setSignUp()
            }
            R.id.btn_looking_service -> {
                (requireActivity() as AuthActivity).userType = USER_TYPE.TYPE_CLIENT
                (requireActivity() as AuthActivity).setSignUp()
            }
            R.id.tv_signin -> {
                (requireActivity() as AuthActivity).setSignIn()
            }
        }
    }

    companion object {
        const val TAG = "Select_User"

        fun newInstance(): Fragment {
            return SelectUserFragment()
        }
    }
}
