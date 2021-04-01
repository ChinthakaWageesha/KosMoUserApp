package sl.com.eightdigitz.authentication.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_get_started.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.authentication.presentation.registration.RegisterUser
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUserRegister
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.hideKeyboard

class GetStarted : BaseActivity(), View.OnClickListener {

    private var userRequest: DUserRegister? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        getData()
        btn_get_started.setOnClickListener(this)
    }

    private fun getData(){
        if (intent.hasExtra(IntentParsableConstants.EXTRA_REGISTER_USER)) {
            userRequest = intent.getParcelableExtra(IntentParsableConstants.EXTRA_REGISTER_USER)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_get_started -> {
                val intent = Intent(this, RegisterUser::class.java)
                intent.putExtra(IntentParsableConstants.EXTRA_REGISTER_USER, userRequest)
                startActivityForResult(intent, RequestCodes.CREATE_USER_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.CREATE_USER_REQUEST_CODE && resultCode == ResultCodes.CREATE_USER_RESULT_CODE){
            setResult(ResultCodes.CREATE_USER_RESULT_CODE).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        hideKeyboard()
        setBackground(sl.com.eightdigitz.presentation.R.drawable.ic_sample_sanga)
        super.onResume()
    }
}