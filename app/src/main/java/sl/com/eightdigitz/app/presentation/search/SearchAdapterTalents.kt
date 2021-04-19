package sl.com.eightdigitz.app.presentation.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_talents_horizontal.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.profile.ViewProfile
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class SearchAdapterTalents(
    val talentList: MutableList<DUser>
) : RecyclerView.Adapter<SearchAdapterTalents.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_talents_horizontal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = talentList.size

    fun clear(){
        talentList.clear()
        notifyDataSetChanged()
    }

    fun addTalents(list: MutableList<DUser>){
        talentList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            val talent = talentList[position]

            if (!talent.profilePicture.isNullOrEmpty()) {
                itemView.iv_talent_pic_horizontal.setRoundedImage(
                    url = talent.profilePicture!!,
                    radius = 12
                )
            } else {
                itemView.iv_talent_pic_horizontal.setRoundedImage(
                    url = Constant.KID_IMAGE_URL,
                    radius = 12
                )
            }

            itemView.tv_talent_name_horizontal.text = talent.fullName

            if (!talent.preferences.isNullOrEmpty()){
                for (i in talent.preferences!!.indices){
                    if (talent.preferences!![i].preferenceID == "44a842cd-da2e-46e6-8e21-b5f771fd76f0"){
                        itemView.tv_talent_field_horizontal.text = "Movies"
                    }

                    if (talent.preferences!![i].preferenceID == "0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be"){
                        itemView.tv_talent_field_horizontal.text = "Sports"
                    }
                }
            } else {
                itemView.tv_talent_field_horizontal.text = "Movies"
            }

            itemView.tv_talent_field_horizontal.text = talent.role

            itemView.setOnClickListener {
                val intent = Intent(it.context, ViewProfile::class.java)
                intent.putExtra(IntentParsableConstants.EXTRA_USER, talent)
                it.context.startActivity(intent)
            }
        }
    }
}