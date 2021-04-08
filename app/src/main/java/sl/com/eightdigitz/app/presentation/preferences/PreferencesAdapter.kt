package sl.com.eightdigitz.app.presentation.preferences

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_set_preferences.view.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUserPreference

class PreferencesAdapter(
    private val preferenceList: MutableList<DPreference>,
    private val userPreferenceList: MutableList<DUserPreference>?
) : RecyclerView.Adapter<PreferencesAdapter.ViewHolder>() {

    private var selectedIds: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_set_preferences, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = preferenceList.size

    fun getSelectedIds(): ArrayList<String>{
        return selectedIds
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val preference = preferenceList[position]

            if (!userPreferenceList.isNullOrEmpty()){
                for (i in 0 until preferenceList.size){
                    for (j in 0 until userPreferenceList.size){
                        if (userPreferenceList[j].preferenceID == preferenceList[i].id){
                            itemView.chk_preferences.isChecked = true
                            selectedIds.add(preferenceList[i].id!!)
                        } else {
                            selectedIds.remove(preference.id)
                        }
                    }
                }
            }

            itemView.chk_preferences.text = preference.name
            itemView.chk_preferences.setOnCheckedChangeListener { button, isChecked ->
                if (isChecked){
                    selectedIds.add(preference.id!!)
                } else {
                    selectedIds.remove(preference.id)
                }
            }
        }
    }
}