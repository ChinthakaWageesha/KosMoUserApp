package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_to_proceed.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.userOrders.adapters.OrderStagesAdapter
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.setRoundedImage
import sl.com.eightdigitz.presentation.extensions.startActivity

class OrderToProceed : BaseActivity(), View.OnClickListener {

    private var orderStageList = mutableListOf<String>()

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
        setData()
        setOrderStageAdapter()
        btn_decline_proceed_order.setOnClickListener(this)
        btn_proceed_order.setOnClickListener(this)
    }

    private fun setData() {
        iv_proceed_order_talent_image.setRoundedImage(
            url = Constant.USER_IMAGE_AQUAMAN,
            radius = 12
        )
        tv_proceed_order_talent_name.text = "Norman Munoz"
        tv_proceed_order_talent_field.text = "Actress"
        tv_proceed_order_reference.text = "Order Reference RTX123HJ"
        tv_proceed_order_due_date.text = "Due by 16 Mar 2021, 08:08 PM"
        tv_proceed_order_for_username.text = "Nadia Ismail"
        tv_address_as.text = "Address Nadia Ismail as"
        tv_address_proceed_order_user_as.text = "She/Her"
        tv_proceed_order_message.text = "Roast Message"
        tv_proceed_order_remaining_hrs.text =
            "You have 12 hours remaining to review the order status. \n" + "By default the order will proceed."
    }

    private fun setOrderStageAdapter() {
        orderStageList.add(0, "Reviewing")
        orderStageList.add(1, "Processing")
        orderStageList.add(2, "On Hold")
        rv_proceed_order_stages.adapter = OrderStagesAdapter(orderStageList)
        rv_proceed_order_stages.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_decline_proceed_order -> {
                hideKeyboard()
                onBackPressed()
            }
            //R.id.btn_proceed_order -> startActivity<OrderSummary>()
        }
    }
}