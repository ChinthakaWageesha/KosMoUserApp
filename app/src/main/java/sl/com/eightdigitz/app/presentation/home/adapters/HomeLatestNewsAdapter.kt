package sl.com.eightdigitz.app.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home_latest_news.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class HomeLatestNewsAdapter : RecyclerView.Adapter<HomeLatestNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_latest_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(position)

    override fun getItemCount(): Int = 4

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_home_latest_news.setRoundedImage(
                url = Constant.MOVIE_IMAGE_URL,
                radius = 32
            )

            itemView.tv_latest_news_field.text = "Technology"
            itemView.tv_latest_news_desc.text = "Insurtech startup PasarPolis gets $54 million — Series B"
        }
    }


}