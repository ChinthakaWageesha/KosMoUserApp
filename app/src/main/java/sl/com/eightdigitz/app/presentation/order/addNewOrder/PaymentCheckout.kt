package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_payment_checkout.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.startActivity

class PaymentCheckout : BaseActivity(), View.OnClickListener {

    private var order: DOrder? = null

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
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)){
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
        }

        tv_subtotal.text = "LKR 10,500"
        tv_service_tax.text = "LKR 250"
        tv_applied_promo_code.text = "(LKR 500)"
        tv_total.text = "LKR 10,250"
        btn_next_checkout.setOnClickListener(this)
    }

    private fun gotoConfirm(){
        val intent = Intent(this, ConfirmOrder::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_next_checkout -> gotoConfirm()
        }
    }

}