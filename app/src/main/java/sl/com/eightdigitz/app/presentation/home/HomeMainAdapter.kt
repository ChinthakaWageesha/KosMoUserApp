package sl.com.eightdigitz.app.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home_main.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage


class HomeMainAdapter(
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_main, parent, false)

        val width = recyclerView.width
        val params = view.layoutParams
        params.width = (width * 0.9).toInt()
        view.layoutParams = params
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 4

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_home_main.setRoundedImage(
                url = Constant.POSTER_IMAGE_URL,
                radius = 30
            )
        }
    }
}