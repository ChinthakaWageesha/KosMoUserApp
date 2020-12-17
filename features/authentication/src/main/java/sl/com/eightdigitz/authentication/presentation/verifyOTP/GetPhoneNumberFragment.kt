package sl.com.eightdigitz.authentication.presentation.verifyOTP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.core.presentation.base.BaseFragment

class GetPhoneNumberFragment : BaseFragment() {


    companion object {
        const val GOOGLE_SIGN_IN = 1555
        const val TAG = "get_phone_number"
        fun newInstance() = GetPhoneNumberFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_phone_number, container, false)
    }

    override fun onViewCreated() {

    }


}
