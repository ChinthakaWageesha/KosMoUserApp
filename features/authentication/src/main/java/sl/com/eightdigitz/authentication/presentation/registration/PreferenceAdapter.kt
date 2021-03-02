package sl.com.eightdigitz.authentication.presentation.registration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_preferences.view.*
import sl.com.eightdigitz.authentication.R
import sl.com.eightdigitz.client.apiSupports.models.Preference
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.mapToApiModel

class PreferenceAdapter(
    private val preferenceList: MutableList<DPreference>,
    val onClickPreference: (Preference, Boolean) -> Unit
) : RecyclerView.Adapter<PreferenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_preferences, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        onBind(position)
    }

    override fun getItemCount(): Int = preferenceList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val preference = preferenceList[position]

            itemView.chk_preferences.text = preference.name
            itemView.chk_preferences.setOnCheckedChangeListener { button, isChecked ->
                onClickPreference(preference.mapToApiModel(), isChecked)
            }
        }
    }
}