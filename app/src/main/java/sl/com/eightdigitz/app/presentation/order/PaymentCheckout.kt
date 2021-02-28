package sl.com.eightdigitz.app.presentation.order

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_payment_checkout.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.startActivity

class PaymentCheckout : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_checkout)
        setToolbar()
        init()
    }

    private fun setToolbar(){
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_checkout),
            isHomeUpEnables = true
        )
    }

    private fun init(){
        tv_subtotal.text = "LKR 10,500"
        tv_service_tax.text = "LKR 250"
        tv_applied_promo_code.text = "(LKR 500)"
        tv_total.text = "LKR 10,250"
        btn_next_checkout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_next_checkout -> startActivity<ConfirmOrder>()
        }
    }

}