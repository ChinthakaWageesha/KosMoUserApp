package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_user_orders.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.userOrders.adapters.OrderStageViewPagerAdapter
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.setAppActionBar

class UserOrders : BaseActivity() {

    private var orderStageList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_orders)
        setToolbar()
        setUpHomeViewPager()
    }

    private fun setToolbar(){
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_your_orders),
            isHomeUpEnables = true
        )
    }

    private fun setUpHomeViewPager(){
        orderStageList.add(0,"Ongoing")
        orderStageList.add(1,"Completed")
        orderStageList.add(2,"Declined")
        orderStageList.add(3,"Rejected")
        orderStageList.add(4,"Expired")

        vp_user_orders.adapter = OrderStageViewPagerAdapter(
            fragmentActivity = this,
            orderStageList = orderStageList
        )
        vp_user_orders.isUserInputEnabled = false
        TabLayoutMediator(tab_layout_orders, vp_user_orders) { tab, position ->
            val mTabText = orderStageList[position]
            tab.text = mTabText
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}