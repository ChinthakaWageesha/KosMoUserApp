package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_summary.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.app.presentation.order.userOrders.adapters.OrderStagesAdapter
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.setRoundedImage
import sl.com.eightdigitz.presentation.extensions.showToast

class OrderSummary : BaseActivity() {

    private var orderStageList = mutableListOf<String>()
    private val vmOrder by viewModel<OrderViewModel>()
    private var order: DOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
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
        setOrderSummaryAdapter()
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
            vmOrder.getTalentById(order?.talentID!!)
        }
        vmOrder.liveDataTalent.observe(this, Observer { observerGetTalent(it) })
    }

    private fun setOrderSummaryAdapter() {
        orderStageList.add(0, "Reviewing")
        orderStageList.add(1, "Processing")
        orderStageList.add(2, "On Hold")
        orderStageList.add(3, "Approved")
        orderStageList.add(4, "Expired")
        orderStageList.add(5, "Refund")
        rv_order_summary_stages.adapter = OrderStagesAdapter(orderStageList)
        rv_order_summary_stages.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setDataWithoutUser(){
        iv_order_summary_talent_image.setRoundedImage(
            url = Constant.USER_IMAGE_AQUAMAN,
            radius = 12
        )

        tv_order_summary_talent_name.text = "Chathurika Bandara"
        tv_order_summary_talent_field.text = "Movies"
        tv_order_summary_reference.text = "Order Reference ${order?.id}"
        tv_order_summary_due_date.text = "Due by ${order?.requestedDeliveryDate}"
        tv_order_summary_for_username.text = order?.orderFor
        tv_address_as.text = "Address ${order?.orderFor} as"
        tv_address_order_summary_user_as.text = order?.toPronoun
        tv_order_summary_message.text = order?.orderType

        tv_title_instructions.text = "Your instructions for ${order?.orderFor} are"
        tv_order_summary_instructions.text = order?.orderInstructions
    }

    private fun setData(talent: DUser) {

        if (!talent.profilePicture.isNullOrEmpty()){
            iv_order_summary_talent_image.setRoundedImage(
                url = talent.profilePicture!!,
                radius = 12
            )
        } else {
            iv_order_summary_talent_image.setRoundedImage(
                url = Constant.USER_IMAGE_AQUAMAN,
                radius = 12
            )
        }

        tv_order_summary_talent_name.text = talent.fullName

        if (!talent.preferences.isNullOrEmpty()){
            for (i in talent.preferences!!.indices){
                if (talent.preferences!![i].preferenceID == "44a842cd-da2e-46e6-8e21-b5f771fd76f0"){
                    tv_order_summary_talent_field.text = "Movies"
                }

                if (talent.preferences!![i].preferenceID == "0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be"){
                    tv_order_summary_talent_field.text = "Sports"
                }
            }
        } else {
            tv_order_summary_talent_field.text = "Movies"
        }


        tv_order_summary_reference.text = "Order Reference ${order?.id}"
        tv_order_summary_due_date.text = "Due by ${order?.requestedDeliveryDate}"
        tv_order_summary_for_username.text = order?.orderFor
        tv_address_as.text = "Address ${order?.orderFor} as"
        tv_address_order_summary_user_as.text = order?.toPronoun
        tv_order_summary_message.text = order?.orderType

        tv_title_instructions.text = "Your instructions for ${order?.orderFor} are"
        tv_order_summary_instructions.text = order?.orderInstructions

    }

    private fun observerGetTalent(resource: Resource<DUser>) {
        resource.let {
            when(it.state){
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setData(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    if (it.errorCode == 404){
                        setDataWithoutUser()
                    } else {
                        it.message?.error.toString().showToast(this)
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                setResult(ResultCodes.NEW_ORDER_RESULT_CODE).also {
                    onBackPressed()
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}