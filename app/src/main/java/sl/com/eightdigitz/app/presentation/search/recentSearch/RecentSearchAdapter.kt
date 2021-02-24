package sl.com.eightdigitz.app.presentation.search.recentSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recent_searches.view.*
import kotlinx.android.synthetic.main.item_search_talents.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class RecentSearchAdapter(
    val talentList: MutableList<DUser>,
    val onClearSearch :(DUser) -> Unit
) : RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recent_searches, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = talentList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            val talent = talentList[position]

            itemView.tv_talent_recent_search.text = talent.fullName
            itemView.tv_talent_field_recent_search.text = talent.role

            if (!talent.profilePicture.isNullOrEmpty()){
                itemView.iv_recent_search.setRoundedImage(
                    talent.profilePicture!!,
                    radius = 12
                )
            } else {
                itemView.iv_recent_search.setRoundedImage(
                    url = Constant.USER_IMAGE_AQUAMAN,
                    radius = 12
                )

            }

            itemView.iv_clear_recent_search.setOnClickListener { onClearSearch(talent) }
        }
    }
}