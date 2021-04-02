package sl.com.eightdigitz.app.presentation.order.userOrders.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user_orders.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.NavigationTypes

class UserOrdersAdapter(
    private val orderList: MutableList<DOrder>,
    val onClickOrder: (DOrder, String) -> Unit
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

    private fun setReviewOrder(itemView: View, order: DOrder) {

        itemView.cl_all_order_base.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_snow_gray_round_corner_15dp
        )

        itemView.cv_user_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_pink_oval
        )

        itemView.btn_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_white_corner_round_20dp
        )

        itemView.btn_order.text = "View Order"

        itemView.btn_order.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                sl.com.eightdigitz.presentation.R.color.colorTextGray
            )
        )

        itemView.btn_order.setOnClickListener { onClickOrder(order, NavigationTypes.NAVIGATE_TO_ORDER_SUMMARY) }
    }

    private fun setProcessingOrder(itemView: View, order: DOrder) {

        itemView.cl_all_order_base.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_snow_gray_stroke_orange_corner_round_15dp
        )

        itemView.cv_user_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_dark_blue_oval
        )

        itemView.btn_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_white_corner_round_20dp
        )

        itemView.btn_order.text = "View Order"

        itemView.btn_order.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                sl.com.eightdigitz.presentation.R.color.colorTextGray
            )
        )

        itemView.btn_order.setOnClickListener { onClickOrder(order, NavigationTypes.NAVIGATE_TO_ORDER_SUMMARY) }
    }

    private fun setOnHoldOrder(itemView: View, order: DOrder) {

        itemView.cl_all_order_base.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_snow_gray_stroke_orange_corner_round_15dp
        )

        itemView.cv_user_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_orange_oval
        )

        itemView.btn_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_orange_corner_round_20dp
        )

        itemView.btn_order.text = "Review Order"

        itemView.btn_order.setTextColor(
            ContextCompat.getColor(
                itemView.context,
                sl.com.eightdigitz.presentation.R.color.colorWhite
            )
        )

        itemView.btn_order.setOnClickListener { onClickOrder(order, NavigationTypes.NAVIGATE_TO_REVIEW_ORDER) }
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

            if (order.stage.equals("OrderAccepted") || order.stage.equals("TalentAccepted")) {
                setProcessingOrder(itemView, order)
            } else if (order.stage.equals("TalentDelivered") || order.stage.equals("OrderReviewRejected")) {
                setOnHoldOrder(itemView, order)
            } else {
                setReviewOrder(itemView, order)
            }
        }
    }
}