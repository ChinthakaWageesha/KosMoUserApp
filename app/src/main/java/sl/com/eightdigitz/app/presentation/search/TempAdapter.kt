package sl.com.eightdigitz.app.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_talents.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

data class TempAdapter(
    private val imageUrl: String
) : RecyclerView.Adapter<TempAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_talents, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 6

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_talent_pro_pic.setRoundedImage(
                url = imageUrl,
                radius = 12
            )

            itemView.tv_talent_name.text = "Tom Hardy"
            itemView.tv_talent_field.text = "Movies"
        }
    }
}
