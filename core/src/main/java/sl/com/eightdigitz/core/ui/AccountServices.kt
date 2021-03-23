package sl.com.eightdigitz.core.ui

import android.os.Bundle
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setAppActionBar

class AccountServices : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_services)
        setToolbar()
    }

    private fun setToolbar(){
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_account_services),
            isHomeUpEnables = true
        )
    }
}