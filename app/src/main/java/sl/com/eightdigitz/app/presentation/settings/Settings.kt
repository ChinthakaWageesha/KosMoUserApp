package sl.com.eightdigitz.app.presentation.settings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_settings.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.showToast
import java.util.ArrayList

class Settings : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        init()
    }

    private fun init() {
        setEventTypeSpinner()
        cl_logout.setOnClickListener(this)
    }

    private fun setEventTypeSpinner() {
        val eventTypes: ArrayList<String> = ArrayList()

        eventTypes.add("English")
        eventTypes.add("Sinhala")
        eventTypes.add("Tamil")
        eventTypes.add("Hindi")

        if (sp_language_settings != null) {
            val adapter = ArrayAdapter(
                this,
                sl.com.eightdigitz.presentation.R.layout.item_spinner,
                sl.com.eightdigitz.presentation.R.id.tv_spinner_category_name,
                eventTypes
            )

            sp_language_settings.adapter = adapter

            sp_language_settings.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int,
                    id: Long
                ) {
                    eventTypes[position].showToast(this@Settings)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_logout -> "Logout".showToast(this)
        }
    }
}