package sl.com.eightdigitz.authentication.presentation.registration

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_post_register.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.AuthActivity
import sl.com.eightdigitz.core.base.BaseFragment

class PostRegister : BaseFragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_register, container, false)
    }

    override fun onViewCreated() {
       init()
    }

    @SuppressLint("SetTextI18n")
    private fun init(){
        tv_hi_user_txt.text = "Hi ${(requireActivity() as AuthActivity).fullName},"
        btn_lets_do_it.setOnClickListener(this)
    }

    companion object {
        const val TAG = "post_register"

        fun newInstance(): Fragment {
            return PostRegister()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_lets_do_it -> {
                (requireActivity() as AuthActivity).authSuccess()
            }
        }
    }
}