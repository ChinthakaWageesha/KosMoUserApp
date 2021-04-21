package sl.com.eightdigitz.app.presentation.order.userOrders

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_order_to_proceed.*
import kotlinx.android.synthetic.main.activity_order_to_proceed.tv_address_as
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.client.apiSupports.requests.UpdateOrderStatusRequest
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUpdateOrderStatus
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class OrderToProceed : BaseActivity(), View.OnClickListener {

    private val vmOrder by viewModel<OrderViewModel>()
    private var orderStageList = mutableListOf<String>()
    private var order: DOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_to_proceed)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_order_summary),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
            withNetwork({
                vmOrder.getTalentById(order?.talentID!!)
            }, {
                showAlert(message = Msg.INTERNET_ISSUE)
            })
        }
        vmOrder.liveDataTalent.observe(this, Observer { observerGetTalent(it) })
        vmOrder.liveDataUpdateStatus.observe(this, Observer { observerDeclineOrder(it) })
        btn_decline_proceed_order.setOnClickListener(this)
        btn_proceed_order.setOnClickListener(this)
    }

    private fun declineOrder() {
        val request = UpdateOrderStatusRequest()
        request.orderId = order?.id
        request.stage = "UserDeclined"
        request.updatedBy = currentLoggedUser!!.id

        showConfirm(
            title = Msg.TITLE_CONFIRMATION,
            message = "Are you sure you want to decline this order?",
            negativeText = "No",
            positiveText = "Yes",
            callback = object : Callback {
                override fun onPositiveClicked() {
                    withNetwork({
                        vmOrder.updateOrderStatus(request)
                    }, {
                        showAlert(message = Msg.INTERNET_ISSUE)
                    })
                }

                override fun onNegativeClicked() {

                }

            }
        )
    }

    private fun setData(talent: DUser) {
        cl_decline_proceed_main_base.makeVisible()
        tv_no_talent.makeGone()

        if (!talent.profilePicture.isNullOrEmpty()) {
            iv_proceed_order_talent_image.setRoundedImage(
                url = talent.profilePicture!!,
                radius = 12
            )
        } else {
            iv_proceed_order_talent_image.setRoundedImage(
                url = Constant.USER_IMAGE_AQUAMAN,
                radius = 12
            )
        }

        tv_proceed_order_talent_name.text = talent.fullName

        if (!talent.preferences.isNullOrEmpty()) {
            for (i in talent.preferences!!.indices) {
                if (talent.preferences!![i].preferenceID == "44a842cd-da2e-46e6-8e21-b5f771fd76f0") {
                    tv_proceed_order_talent_field.text = "Movies"
                }

                if (talent.preferences!![i].preferenceID == "0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be") {
                    tv_proceed_order_talent_field.text = "Sports"
                }
            }
        } else {
            tv_proceed_order_talent_field.text = "Movies"
        }

        tv_proceed_order_reference.text = "Order Reference ${order?.id}"
        tv_proceed_order_due_date.text = "Due by ${order?.requestedDeliveryDate}"
        tv_proceed_order_for_username.text = order?.orderFor
        tv_address_as.text = "Address ${order?.orderFor} as"
        tv_address_proceed_order_user_as.text = order?.toPronoun
        tv_proceed_order_message.text = order?.orderType
        tv_instructions.text = order?.orderInstructions
        tv_proceed_order_remaining_hrs.text =
            "You have 24 hours remaining to review the order status. \n" + "By default the order will proceed."
    }

    private fun observerGetTalent(resource: Resource<DUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setData(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    if (it.errorCode == 404) {
                        cl_decline_proceed_main_base.makeGone()
                        tv_no_talent.makeVisible()
                    } else {
                        it.message?.error.toString().showToast(this)
                    }
                }
            }
        }
    }

    private fun observerDeclineOrder(resource: Resource<DUpdateOrderStatus>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    "You have declined this order".showToast(this)
                    setResult(ResultCodes.DECLINE_ORDER_RESULT_CODE)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error?.showToast(this)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_decline_proceed_order -> declineOrder()
            R.id.btn_proceed_order -> {
                val intent = Intent(this, OrderSummary::class.java)
                intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
                startActivity(intent).also {
                    finish()
                }
            }
        }
    }
}