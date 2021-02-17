package sl.com.eightdigitz.app.presentation.search.preferenceSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_talent_by_preference.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class SearchTalentByPreferenceAdapter :
    RecyclerView.Adapter<SearchTalentByPreferenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_talent_by_preference, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = 20

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            itemView.iv_search_talent_by_preference.setRoundedImage(
                url = Constant.USER_IMAGE_URL,
                radius = 12
            )

            itemView.tv_talent_name_search_preference.text = "Otara Gunawardhana"
            itemView.tv_talent_field_search_preference.text = "Entrepreneur"

        }

    }
}