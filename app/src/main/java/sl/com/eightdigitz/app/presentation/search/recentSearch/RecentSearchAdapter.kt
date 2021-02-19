package sl.com.eightdigitz.app.presentation.search.recentSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recent_searches.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class RecentSearchAdapter : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recent_searches, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 5

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_recent_search.setRoundedImage(
                url = Constant.USER_IMAGE_URL,
                radius = 12
            )

            itemView.tv_talent_recent_search.text = "Avril Lavinge"
            itemView.tv_talent_field_recent_search.text = "Athlete"

        }
    }


}