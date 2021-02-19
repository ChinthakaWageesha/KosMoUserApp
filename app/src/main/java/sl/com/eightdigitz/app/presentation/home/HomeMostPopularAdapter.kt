package sl.com.eightdigitz.app.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home_most_popular.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class HomeMostPopularAdapter : RecyclerView.Adapter<HomeMostPopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_most_popular, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 6

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_home_most_popular.setRoundedImage(
                url = Constant.MOVIE_IMAGE_URL_1,
                radius = 12
            )

            itemView.tv_most_popular_desc.text = "Adwoods keyword research for"
            itemView.tv_most_popular_date.text = "Yesterday"
        }
    }
}