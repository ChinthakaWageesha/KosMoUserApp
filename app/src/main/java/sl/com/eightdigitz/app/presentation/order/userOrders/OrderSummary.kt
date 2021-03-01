package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_summary.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.userOrders.adapters.OrderStagesAdapter
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class OrderSummary : BaseActivity() {

    private var orderStageList = mutableListOf<String>()

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
        setData()
        setOrderSummaryAdapter()
    }

    private fun setData() {
        iv_order_summary_talent_image.setRoundedImage(
            url = Constant.USER_IMAGE_AQUAMAN,
            radius = 12
        )
        tv_order_summary_talent_name.text = "Norman Munoz"
        tv_order_summary_talent_field.text = "Actress"
        tv_order_summary_reference.text = "Order Reference RTX123HJ"
        tv_order_summary_due_date.text = "Due by 16 Mar 2021, 08:08 PM"
        tv_order_summary_for_username.text = "Nadia Ismail"
        tv_address_as.text = "Address Nadia Ismail as"
        tv_address_order_summary_user_as.text = "She/Her"
        tv_order_summary_message.text = "Roast Message"
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

    override fun onSupportNavigateUp(): Boolean {
        goBack()
        return super.onSupportNavigateUp()
    }
}