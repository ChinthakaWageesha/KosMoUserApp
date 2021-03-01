package sl.com.eightdigitz.app.presentation.order.userOrders.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import sl.com.eightdigitz.app.presentation.order.userOrders.OrderStageItem
import sl.com.eightdigitz.presentation.IntentParsableConstants

class OrderStageViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val orderStageList: MutableList<String>
): FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = orderStageList.size

    override fun createFragment(position: Int): Fragment {
        val title = orderStageList[position]

        var fragment: Fragment? = null
        val bundle = Bundle()


        fragment = OrderStageItem.newInstance()
        bundle.putString(IntentParsableConstants.EXTRA_ORDER_STAGE_ITEM, title)
        fragment.arguments = bundle

        return fragment
    }
}