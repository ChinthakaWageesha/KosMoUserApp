package sl.com.eightdigitz.app.presentation.order.userOrders.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user_orders.view.*
import sl.com.eightdigitz.app.R

class UserOrdersAdapter(
    val onClickOrder: (String) -> Unit
) : RecyclerView.Adapter<UserOrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_orders, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 6

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            itemView.tv_order_reference.text = "Reference RTX123HJ"
            itemView.tv_user_order_type.text = "Birthday Wish"
            itemView.tv_order_for.text = "by Sanga"
            itemView.tv_order_date.text = "16 Mar 2021, 08:08 PM"

            itemView.btn_order.text = "Review Order"
            itemView.btn_order.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    sl.com.eightdigitz.presentation.R.color.colorWhite
                )
            )

            itemView.btn_order.setOnClickListener { onClickOrder(position.toString()) }
        }
    }
}