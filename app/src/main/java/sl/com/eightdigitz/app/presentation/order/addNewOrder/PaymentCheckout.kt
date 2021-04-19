package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_payment_checkout.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DTalentRate
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.startActivity

class PaymentCheckout : BaseActivity(), View.OnClickListener {

    private var order: DOrder? = null
    private var talentRate: DTalentRate? = null

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

        if (intent.hasExtra(IntentParsableConstants.EXTRA_TALENT_RATE)){
            talentRate = intent.getParcelableExtra(IntentParsableConstants.EXTRA_TALENT_RATE)

            tv_subtotal.text = talentRate?.rate.toString()
            tv_service_tax.text = ((talentRate?.rate?.times(talentRate?.VATPercentage!!))?.div(100)).toString()
            //tv_applied_promo_code.text = "(LKR 500)"

            tv_total.text = (talentRate?.rate?.minus((talentRate?.rate?.times(talentRate?.VATPercentage!!))?.div(100)!!)).toString()
        }


        btn_next_checkout.setOnClickListener(this)
    }

    private fun gotoConfirm(){
        val intent = Intent(this, ConfirmOrder::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
        startActivityForResult(intent, RequestCodes.NEW_ORDER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.NEW_ORDER_REQUEST_CODE && resultCode == ResultCodes.NEW_ORDER_RESULT_CODE){
            setResult(ResultCodes.NEW_ORDER_RESULT_CODE).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_next_checkout -> gotoConfirm()
        }
    }

}