package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_confirm_order.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.app.presentation.order.userOrders.OrderSummary
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class ConfirmOrder : BaseActivity(), View.OnClickListener {

    private val vmOrder by viewModel<OrderViewModel>()
    private var order: DOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_confirm_order),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
        }
        vmOrder.liveDataPlaceOrder.observe(this, Observer { observerNewOrder(it) })
        btn_place_order.setOnClickListener(this)
    }

    @SuppressLint("MissingPermission")
    private fun placeOrder(){
        val orderRequest = NewOrderRequest()

        orderRequest.orderFor = order?.orderFor
        orderRequest.toPronoun = order?.toPronoun
        orderRequest.fromPronoun = order?.fromPronoun
        orderRequest.orderInstructions = order?.orderInstructions
        orderRequest.deliveryDate = "2021-05-06"
        orderRequest.stage = "New"
        orderRequest.userID = currentLoggedUser?.id
        orderRequest.talentID = order?.talentID
        orderRequest.orderType = "Default"

        withNetwork({
            vmOrder.placeNewOrder(orderRequest)
        },{
            showAlert(message = Msg.INTERNET_ISSUE)
        })

    }

    private fun observerNewOrder(resource: Resource<DOrder>){
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    orderConfirmDialog(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun orderConfirmDialog(order: DOrder){
        showConfirm(
            title = "Order Received",
            message = "If, for some reason, your request is not fulfilled, the hold on your payment card will be removed in 5-7 business days.",
            negativeText = "FAQs",
            positiveText = "OK",
            callback = object :Callback {
                override fun onPositiveClicked() {
                    val intent = Intent(this@ConfirmOrder, OrderSummary::class.java)
                    intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
                    startActivityForResult(intent, RequestCodes.NEW_ORDER_REQUEST_CODE)
                }

                override fun onNegativeClicked() {

                }
            }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.NEW_ORDER_REQUEST_CODE && resultCode == ResultCodes.NEW_ORDER_RESULT_CODE){
            setResult(ResultCodes.NEW_ORDER_RESULT_CODE).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_place_order -> placeOrder()
        }
    }
}