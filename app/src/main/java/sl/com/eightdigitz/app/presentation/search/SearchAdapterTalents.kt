package sl.com.eightdigitz.app.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_talents.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class SearchAdapterTalents(
    val talentList: MutableList<DUser>
) : RecyclerView.Adapter<SearchAdapterTalents.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_talents, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = talentList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            val talent = talentList[position]

            if (!talent.profilePicture.isNullOrEmpty()) {
                itemView.iv_talent_pro_pic.setRoundedImage(
                    url = talent.profilePicture!!,
                    radius = 12
                )
            } else {
                itemView.iv_talent_pro_pic.setRoundedImage(
                    url = Constant.USER_IMAGE_AQUAMAN,
                    radius = 12
                )
            }

            itemView.tv_talent_name.text = talent.fullName
            itemView.tv_talent_field.text = talent.role
        }
    }

}