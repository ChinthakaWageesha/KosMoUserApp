package sl.com.eightdigitz.app.presentation.order

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_confirm_order.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setAppActionBar

class ConfirmOrder : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)
        setToolbar()
        init()
    }

    private fun setToolbar(){
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_confirm_order),
            isHomeUpEnables = true
        )
    }

    private fun init(){
        btn_place_order.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        goBack()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_place_order -> {

            }
        }
    }
}