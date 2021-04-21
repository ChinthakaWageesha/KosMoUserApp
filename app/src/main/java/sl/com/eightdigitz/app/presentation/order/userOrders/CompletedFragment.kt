package sl.com.eightdigitz.app.presentation.order.userOrders

import android.annotation.SuppressLint
import android.content.Intent
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
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.setEmptyView
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.withNetwork

class CompletedFragment : BaseFragment(), (DOrder, String) -> Unit {

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

    private fun init() {
        setAdapter()
        getOrders()
        vmOrder.liveDataGetOrders.observe(this, Observer { observerGetOrdersByStage(it) })
    }

    private fun setAdapter() {
        rv_completed_orders.adapter = UserOrdersAdapter(mutableListOf(), this)
        rv_completed_orders.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("MissingPermission")
    private fun getOrders() {
        activity?.withNetwork({
            vmOrder.getOrdersByStages("OrderReviewSuccess,Delivered")
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun observerGetOrdersByStage(resource: Resource<List<DOrder>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (rv_completed_orders.adapter as UserOrdersAdapter).clear()
                    (rv_completed_orders.adapter as UserOrdersAdapter).addOrderList(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    if (it.errorCode == 404) {
                        rv_completed_orders.setEmptyView(tv_no_data_completed_orders, 0)
                    } else {
                        it.message?.error.toString().showToast(context!!)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.DECLINE_ORDER_REQUEST_CODE && resultCode == ResultCodes.DECLINE_ORDER_RESULT_CODE) {
            getOrders()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        fun newInstance(): Fragment {
            return CompletedFragment()
        }
    }

    override fun invoke(order: DOrder, navigationType: String) {
        if (!order.shoutOutURL.isNullOrEmpty()) {
            val intent = Intent(context!!, ViewShoutOutVideo::class.java)
            intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
            startActivity(intent)
        } else {
            val intent = Intent(context!!, OrderSummary::class.java)
            intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
            startActivityForResult(intent, RequestCodes.DECLINE_ORDER_REQUEST_CODE)
        }
    }
}