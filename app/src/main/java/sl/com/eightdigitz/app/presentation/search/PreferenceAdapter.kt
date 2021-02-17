package sl.com.eightdigitz.app.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_home_preferences.view.*
import sl.com.eightdigitz.app.R

class PreferenceAdapter(
    val preferenceList: MutableList<String>,
    val onClickPreference: (String) -> Unit
) : RecyclerView.Adapter<PreferenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_preferences, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = preferenceList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val preference = preferenceList[position]

            itemView.btn_preference.text = preference

            itemView.btn_preference.setOnClickListener {
                onClickPreference(preference)
            }
        }
    }
}