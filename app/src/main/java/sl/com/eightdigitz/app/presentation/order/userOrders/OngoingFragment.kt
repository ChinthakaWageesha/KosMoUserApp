package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_expired.*
import kotlinx.android.synthetic.main.fragment_ongoing.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.app.presentation.order.userOrders.adapters.UserOrdersAdapter
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.setEmptyView
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.showToast

class OngoingFragment : BaseFragment(), (DOrder) -> Unit {

    private val vmOrder by viewModel<OrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ongoing, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        setAdapter()
        vmOrder.getOrdersByStages(
            stages = "New,OrderAccepted,TalentAccepted,TalentDelivered,OrderReviewRejected"
        )
        vmOrder.liveDataGetOrders.observe(this, Observer { observerGetOrdersByStage(it) })
    }

    private fun setAdapter() {
        rv_ongoing_orders.adapter = UserOrdersAdapter(mutableListOf(), this)
        rv_ongoing_orders.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }

    private fun observerGetOrdersByStage(resource: Resource<List<DOrder>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (rv_ongoing_orders.adapter as UserOrdersAdapter).clear()
                    rv_ongoing_orders.setEmptyView(tv_no_data_ongoing_orders, it.data!!.size)
                    (rv_ongoing_orders.adapter as UserOrdersAdapter).addOrderList(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(context!!)
                }
            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            return OngoingFragment()
        }
    }

    override fun invoke(order: DOrder) {
        order.id?.showToast(context!!)
    }
}