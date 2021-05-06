package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_payment_checkout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DPromoCode
import sl.com.eightdigitz.core.model.domain.DTalentRate
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class PaymentCheckout : BaseActivity(), View.OnClickListener {

    private var order: DOrder? = null
    private var talentRate: DTalentRate? = null
    private var subTotal: Int? = null
    private var serviceTax: Int? = null
    private var total: Int? = null
    private val vmPayment by viewModel<OrderViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_checkout)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_checkout),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
        }

        if (intent.hasExtra(IntentParsableConstants.EXTRA_TALENT_RATE)) {
            talentRate = intent.getParcelableExtra(IntentParsableConstants.EXTRA_TALENT_RATE)

            subTotal = talentRate?.rate
            tv_subtotal.text = subTotal.toString()

            serviceTax = subTotal?.times(talentRate?.VATPercentage!!)?.div(100)
            tv_service_tax.text = serviceTax.toString()
        }

        vmPayment.liveDataGetOffers.observe(this, Observer { observerGetOffers(it) })
        btn_add_promo_code.setOnClickListener(this)
        btn_next_checkout.setOnClickListener(this)
    }

    private fun getOfferByPromoCode() {
        withNetwork({
            vmPayment.getOrdersByPromoCode(et_promo_code.getStringTrim())
        },{
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun observerGetOffers(resource: Resource<DPromoCode>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setPromoCode(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    calculateTotalWithoutDiscount()
                    it.message?.error?.showToast(this)
                }
            }
        }
    }

    private fun setPromoCode(promoCode: DPromoCode) {
        var discount = 0
        tv_title_applied_promo_code.makeVisible()
        tv_applied_promo_code.makeVisible()
        if (promoCode.discountAmount.toString() != "0"){
            discount = subTotal?.minus(promoCode.discountAmount!!)!!.toInt()
        }

        if (promoCode.discountPercentage.toString() != "0"){
            discount = subTotal?.times(promoCode.discountPercentage!!)?.div(100)!!.toInt()
        }

        calculateTotalWithDiscount(discount)
    }

    private fun calculateTotalWithoutDiscount(){
        tv_title_applied_promo_code.makeGone()
        tv_applied_promo_code.makeGone()
        total = subTotal?.plus(serviceTax!!)
        tv_total.text = total.toString()
    }

    private fun calculateTotalWithDiscount(discount: Int){
        total = subTotal?.plus(serviceTax!!)
        tv_total.text = total?.minus(discount).toString()
    }

    private fun gotoConfirm() {
        val intent = Intent(this, ConfirmOrder::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
        startActivityForResult(intent, RequestCodes.NEW_ORDER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.NEW_ORDER_REQUEST_CODE && resultCode == ResultCodes.NEW_ORDER_RESULT_CODE) {
            setResult(ResultCodes.NEW_ORDER_RESULT_CODE).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_promo_code -> getOfferByPromoCode()
            R.id.btn_next_checkout -> gotoConfirm()
        }
    }

}