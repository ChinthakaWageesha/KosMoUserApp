package sl.com.eightdigitz.app.presentation.order.userOrders.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user_orders.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DOrder

class UserOrdersAdapter(
    private val orderList: MutableList<DOrder>,
    val onClickOrder: (DOrder) -> Unit
) : RecyclerView.Adapter<UserOrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_orders, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = orderList.size

    fun addOrderList(list: MutableList<DOrder>) {
        orderList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        orderList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            val order = orderList[position]

            itemView.tv_order_reference.text = "Reference ${order.id}"
            itemView.tv_user_order_type.text = "${order.orderType} Wish"

            if (!order.talentName.isNullOrEmpty()) {
                itemView.tv_order_for.text = "by ${order.talentName}"
            } else {
                itemView.tv_order_for.text = "by N?A"
            }

            itemView.tv_order_date.text = order.requestedDeliveryDate

            itemView.btn_order.text = "Review Order"
            itemView.btn_order.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    sl.com.eightdigitz.presentation.R.color.colorWhite
                )
            )

            itemView.btn_order.setOnClickListener { onClickOrder(order) }
        }
    }
}