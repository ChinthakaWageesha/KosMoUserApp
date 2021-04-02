package sl.com.eightdigitz.app.presentation.order.userOrders.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_order_stages.view.*
import sl.com.eightdigitz.app.R

class OrderStagesAdapter(
    private val orderStageList: MutableList<String>
) : RecyclerView.Adapter<OrderStagesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_order_stages, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = orderStageList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val orderStage = orderStageList[position]

            itemView.tv_order_stages.text = orderStage
            if (orderStage == "Reviewing"){
                itemView.cv_order_stages.setBackgroundResource(
                    sl.com.eightdigitz.presentation.R.drawable.bg_pink_oval
                )
            }

            if (orderStage == "Processing"){
                itemView.cv_order_stages.setBackgroundResource(
                    sl.com.eightdigitz.presentation.R.drawable.bg_dark_blue_oval
                )
            }

            if (orderStage == "On Hold"){
                itemView.cv_order_stages.setBackgroundResource(
                    sl.com.eightdigitz.presentation.R.drawable.bg_orange_oval
                )
            }

            if (orderStage == "Approved"){
                itemView.cv_order_stages.setBackgroundResource(
                    sl.com.eightdigitz.presentation.R.drawable.bg_pink_oval
                )
            }

            if (orderStage == "Expired"){
                itemView.cv_order_stages.setBackgroundResource(
                    sl.com.eightdigitz.presentation.R.drawable.bg_gray_oval
                )
            }

            if (orderStage == "Refund"){
                itemView.cv_order_stages.setBackgroundResource(
                    sl.com.eightdigitz.presentation.R.drawable.bg_light_green_oval
                )
            }
        }
    }
}