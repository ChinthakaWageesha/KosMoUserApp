package sl.com.eightdigitz.app.presentation.search.recentSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recent_searches.view.*
import kotlinx.android.synthetic.main.item_search_talent_vertical.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.ViewTypes
import sl.com.eightdigitz.presentation.extensions.setRoundedImage

class RecentSearchAdapter(
    var viewType: Int,
    val onClearSearch :(DUser, Int) -> Unit
) : RecyclerView.Adapter<RecentSearchAdapter.BaseViewHolder<*>>() {

    private val talentList: MutableList<DUser> = mutableListOf()

    companion object {
        private const val TYPE_RECENT_TALENT = 1
        private const val TYPE_TALENT = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType){
            TYPE_RECENT_TALENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_recent_searches, parent, false)
                ViewHolderRecentTalent(view)
            }

            TYPE_TALENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search_talent_vertical, parent, false)
                ViewHolderTalent(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(viewType){
            ViewTypes.RECENT_TALENTS -> TYPE_RECENT_TALENT
            ViewTypes.TALENTS -> TYPE_TALENT
            else -> throw IllegalArgumentException("Invalid type of data")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) = holder.run {
        val talent = talentList[position]

        when(holder){
            is ViewHolderRecentTalent -> holder.bind(talent)
            is ViewHolderTalent -> holder.bind(talent)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = talentList.size

    fun addTalentList(list: MutableList<DUser>){
        talentList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        talentList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolderRecentTalent(itemView: View) : BaseViewHolder<DUser>(itemView) {
        override fun bind(item: DUser) {

            itemView.tv_talent_recent_search.text = item.fullName

            if (!item.preferences.isNullOrEmpty()){
                for (i in item.preferences!!.indices){
                    if (item.preferences!![i].preferenceID == "44a842cd-da2e-46e6-8e21-b5f771fd76f0"){
                        itemView.tv_talent_field_recent_search.text = "Movies"
                    }

                    if (item.preferences!![i].preferenceID == "0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be"){
                        itemView.tv_talent_field_recent_search.text = "Sports"
                    }
                }
            } else {
                itemView.tv_talent_field_recent_search.text = "Movies"
            }

            if (!item.profilePicture.isNullOrEmpty()){
                itemView.iv_recent_search.setRoundedImage(
                    item.profilePicture!!,
                    radius = 12
                )
            } else {
                itemView.iv_recent_search.setRoundedImage(
                    url = Constant.KID_IMAGE_URL,
                    radius = 12
                )
            }

            itemView.iv_clear_recent_search.setOnClickListener { onClearSearch(item, TYPE_RECENT_TALENT) }
        }
    }

    inner class ViewHolderTalent(itemView: View): BaseViewHolder<DUser>(itemView){
        override fun bind(item: DUser) {

            itemView.tv_talent_name_vertical.text = item.fullName

            if (!item.preferences.isNullOrEmpty()){
                for (i in item.preferences!!.indices){
                    if (item.preferences!![i].preferenceID == "44a842cd-da2e-46e6-8e21-b5f771fd76f0"){
                        itemView.tv_talent_field_vertical.text = "Movies"
                    }

                    if (item.preferences!![i].preferenceID == "0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be"){
                        itemView.tv_talent_field_vertical.text = "Sports"
                    }
                }
            } else {
                itemView.tv_talent_field_vertical.text = "Movies"
            }

            if (!item.profilePicture.isNullOrEmpty()){
                itemView.iv_talent_pic_vertical.setRoundedImage(
                    item.profilePicture!!,
                    radius = 12
                )
            } else {
                itemView.iv_talent_pic_vertical.setRoundedImage(
                    url = Constant.KID_IMAGE_URL,
                    radius = 12
                )
            }

            itemView.setOnClickListener{onClearSearch(item, TYPE_TALENT)}
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}