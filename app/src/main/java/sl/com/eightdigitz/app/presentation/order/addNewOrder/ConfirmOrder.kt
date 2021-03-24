package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_confirm_order.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.userOrders.OrderSummary
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
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
        vmOrder.liveDataOrder.observe(this, Observer { observerNewOrder(it) })
        btn_place_order.setOnClickListener(this)
    }

    private fun placeOrder(){
        val orderRequest = NewOrderRequest()

        orderRequest.orderFor = order?.orderFor
        orderRequest.toPronoun = order?.toPronoun
        orderRequest.fromPronoun = "Someone else"
        orderRequest.orderInstructions = order?.orderInstructions
        orderRequest.deliveryDate = "2021-03-10"
        orderRequest.stage = "New"
        orderRequest.userID = currentLoggedUser?.id
        orderRequest.talentID = "f95848cb-31a0-4a47-9dde-8eb05483c960"
        orderRequest.orderType = "Default"

        vmOrder.placeNewOrder(orderRequest)
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
                    showAlert(title = Msg.TITLE_ERROR, message = it.message.toString())
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
                    startActivity(intent)
                }

                override fun onNegativeClicked() {

                }
            }
        )
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