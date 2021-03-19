package sl.com.eightdigitz.app.presentation.favourite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_talent_vertical.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.search.viewProfile.ViewProfile
import sl.com.eightdigitz.presentation.extensions.setRoundedImage


class FavouritesAdapter(
    private val imageUrl: String
) : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_talent_vertical, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 10

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_talent_pic_vertical.setRoundedImage(
                url = imageUrl,
                radius = 12
            )

            itemView.tv_talent_name_vertical.text = "Tom Hardy"
            itemView.tv_talent_field_vertical.text = "Movies"

            itemView.setOnClickListener {
                it.context.startActivity(Intent(it.context, ViewProfile::class.java))
            }

        }
    }

}