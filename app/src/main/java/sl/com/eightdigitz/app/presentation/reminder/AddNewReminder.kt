package sl.com.eightdigitz.app.presentation.reminder

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_new_reminder.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.showToast
import java.text.SimpleDateFormat
import java.util.*

class AddNewReminder : BaseActivity(), View.OnClickListener {

    private var calender = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_reminder)
        setToolbar()
        init()
    }

    private fun setToolbar(){
        supportActionBar?.setAppActionBar(
            this,
            title = getString(sl.com.eightdigitz.presentation.R.string.heading_new_reminder),
            isHomeUpEnables = true
        )
    }

    private fun init(){
        setEventTypeSpinner()
        et_event_date.setOnClickListener(this)
    }



    private fun setDatePicker() {
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, monthOfYear)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

        val mDatePickerDialog = DatePickerDialog(
            this,
            date,
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )

        mDatePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        mDatePickerDialog.show()
    }

    private fun updateLabel() {
        val myFormat = "dd MMMM yyyy"
        val sdfForView = SimpleDateFormat(myFormat, Locale.US)
        et_event_date.setText(sdfForView.format(calender.time))
    }

    private fun setEventTypeSpinner() {
        val eventTypes: ArrayList<String> = ArrayList()

        eventTypes.add("Family")
        eventTypes.add("Business")
        eventTypes.add("Society")
        eventTypes.add("Individual")

        if (sp_event_type != null) {
            val adapter = ArrayAdapter(
                this,
                sl.com.eightdigitz.presentation.R.layout.item_spinner,
                sl.com.eightdigitz.presentation.R.id.tv_spinner_category_name,
                eventTypes
            )

            sp_event_type.adapter = adapter

            sp_event_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?, position: Int,
                    id: Long
                ) {
                    eventTypes[position].showToast(this@AddNewReminder)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.et_event_date -> setDatePicker()
        }
    }
}