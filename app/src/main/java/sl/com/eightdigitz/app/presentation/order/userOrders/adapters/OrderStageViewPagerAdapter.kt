package sl.com.eightdigitz.app.presentation.order.userOrders.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import sl.com.eightdigitz.app.presentation.order.userOrders.*

class OrderStageViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val orderStageList: MutableList<String>
): FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = orderStageList.size

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()

        when(position){
            0 -> fragment = OngoingFragment.newInstance()
            1 -> fragment = CompletedFragment.newInstance()
            2 -> fragment = DeclinedFragment.newInstance()
            3 -> fragment = RejectedFragment.newInstance()
            4 -> fragment = ExpiredFragment.newInstance()
        }

        return fragment
    }
}