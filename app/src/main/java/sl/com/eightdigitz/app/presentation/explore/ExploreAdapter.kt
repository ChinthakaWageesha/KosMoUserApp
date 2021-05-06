package sl.com.eightdigitz.app.presentation.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_explore_user.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.extensions.loadImageRound

class ExploreAdapter(
    private val exploreUserList: MutableList<DUser>
) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_explore_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = exploreUserList.size


    fun addExploreUsers(list: MutableList<DUser>) {
        exploreUserList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        exploreUserList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {

            val talent = exploreUserList[position]

            if (!talent.profileVideo.isNullOrEmpty()) {
                itemView.talent_video.setSource(talent.profileVideo)
            }

            if (!talent.profilePicture.isNullOrEmpty()) {
                itemView.iv_explore_user_image.loadImageRound(
                    url = talent.profilePicture!!,
                    placeholder = sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture
                )
            } else {
                itemView.iv_explore_user_image.setBackgroundResource(sl.com.eightdigitz.presentation.R.drawable.ic_ph_profile_profile_picture)
            }

            itemView.tv_explore_user_name.text = talent.fullName
            itemView.tv_explore_user_field.text = "Sports, Movies"

        }
    }
}