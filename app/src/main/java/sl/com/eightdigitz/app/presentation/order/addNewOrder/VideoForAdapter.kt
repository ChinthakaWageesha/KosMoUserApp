package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_video_for.view.*
import sl.com.eightdigitz.app.R

class VideoForAdapter(
    val titleList: MutableList<String>,
    val onClickVideoFor: (String) -> Unit
) : RecyclerView.Adapter<VideoForAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video_for, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = titleList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            itemView.chk_video_for.text = titleList[position]
            itemView.setOnClickListener { onClickVideoFor(titleList[position]) }
        }
    }
}