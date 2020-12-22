package sl.com.eightdigitz.app.presentation.change_password

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.validate
import sl.com.eightdigitz.presentation.extensions.setActionBar
import sl.com.eightdigitz.presentation.extensions.getString
import sl.com.eightdigitz.presentation.extensions.alert
import sl.com.eightdigitz.presentation.extensions.showAlert
import kotlinx.android.synthetic.main.activity_change_password.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : BaseActivity(), View.OnClickListener {

    private val vm by viewModel<ChangePasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.setActionBar(this, "Change Password", true)

        vm.liveData.observe(this, Observer { updatePassword(it) })

        btn_save.setOnClickListener(this)
    }

    private fun updatePassword(resource: Resource<Success>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    this.alert(getString(R.string.msg_success), it.message) {
                        positiveButton(getString(R.string.action_ok)) {
                            finish()
                        }
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR,it.message.toString())
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_save -> {
                val isPassword =
                    et_password.validate(Msg.MIN_REQUIREMENT_PASSWORD) { s -> s.length >= 6 }
                val isCurrentPassowrd =
                    et_current_password.validate(Msg.REQUIRED_PASSWORD) { s -> s.isNotEmpty() }
                val isPasswordConfirmed =
                    et_confirm_password.validate(Msg.NOT_MATCH) { s -> s == et_password.getString() }

                /*withNetwork(
                    {
                        if (isPassword && isCurrentPassowrd && isPasswordConfirmed) {
                            vm.changePassword(et_current_password.getString(), et_password.getString())
                        }
                    },
                    {
                        Msg.INTERNET_ISSUE.showToast(this)
                    })*/
            }
        }

    }
}
