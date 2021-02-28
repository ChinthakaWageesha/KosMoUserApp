package sl.com.eightdigitz.app.presentation.search.preferenceSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_talent_by_preference.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.search.viewProfile.ViewProfile
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class SearchTalentByPreferenceAdapter(
    private val talentList: MutableList<DUser>
) :
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

    override fun getItemCount(): Int = talentList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            val talent = talentList[position]

            if (!talent.profilePicture.isNullOrEmpty()) {
                itemView.iv_search_talent_by_preference.setRoundedImage(
                    url = talent.profilePicture!!,
                    radius = 12
                )
            } else {
                itemView.iv_search_talent_by_preference.setRoundedImage(
                    url = Constant.USER_IMAGE_AQUAMAN,
                    radius = 12
                )
            }
            itemView.tv_talent_name_search_preference.text = talent.fullName
            itemView.tv_talent_field_search_preference.text = talent.role

            itemView.setOnClickListener {
                val intent = Intent(it.context, ViewProfile::class.java)
                intent.putExtra(IntentParsableConstants.EXTRA_USER, talent)
                it.context.startActivity(intent)
            }
        }

    }
}