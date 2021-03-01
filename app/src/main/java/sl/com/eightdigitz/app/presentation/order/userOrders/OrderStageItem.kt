package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_order_stage_item.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.userOrders.adapters.UserOrdersAdapter
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.presentation.extensions.startActivity

class OrderStageItem : BaseFragment(), (String) -> Unit {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_stage_item, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        rv_user_orders.adapter = UserOrdersAdapter(this)
        rv_user_orders.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    companion object {
        const val TAG = "order_stage_item"

        fun newInstance(): Fragment {
            return OrderStageItem()
        }
    }

    override fun invoke(position: String) {
        context?.startActivity<OrderToProceed>()
    }
}