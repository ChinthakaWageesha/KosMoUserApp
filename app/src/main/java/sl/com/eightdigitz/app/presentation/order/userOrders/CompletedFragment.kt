package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_completed.*
import kotlinx.android.synthetic.main.fragment_expired.*
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

class CompletedFragment : BaseFragment(), (DOrder) -> Unit {

    private val vmOrder by viewModel<OrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init(){
        setAdapter()
        vmOrder.getOrdersByStages(
            stages = "OrderReviewSuccess"
        )
        vmOrder.liveDataGetOrders.observe(this, Observer { observerGetOrdersByStage(it) } )
    }

    private fun setAdapter() {
        rv_completed_orders.adapter = UserOrdersAdapter(mutableListOf(), this)
        rv_completed_orders.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }

    private fun observerGetOrdersByStage(resource: Resource<List<DOrder>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (rv_completed_orders.adapter as UserOrdersAdapter).clear()
                    rv_completed_orders.setEmptyView(tv_no_data_completed_orders, it.data!!.size)
                    (rv_completed_orders.adapter as UserOrdersAdapter).addOrderList(it.data!!.toMutableList())
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
            return CompletedFragment()
        }
    }

    override fun invoke(order: DOrder) {
        order.id?.showToast(context!!)
    }
}