package sl.com.eightdigitz.app.presentation.reminder

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener
import com.skyhope.eventcalenderlibrary.model.DayContainerModel
import com.skyhope.eventcalenderlibrary.model.Event
import kotlinx.android.synthetic.main.activity_reminders.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.showToast
import sl.com.eightdigitz.presentation.extensions.startActivity
import java.util.*

class Reminders : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
        setToolbar()
        setTempEvent()
        setTempAdapter()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_reminders),
            isHomeUpEnables = true
        )
    }

    private fun setTempEvent() {
        val cal = Calendar.getInstance() //current date and time
        cal.add(Calendar.DAY_OF_MONTH, 1) //add a day
        cal[Calendar.HOUR_OF_DAY] = 23 //set hour to last hour
        cal[Calendar.MINUTE] = 59 //set minutes to last minute
        cal[Calendar.SECOND] = 59 //set seconds to last second
        cal[Calendar.MILLISECOND] = 999 //set milliseconds to last millisecond
        val millis1 = cal.timeInMillis

        cal.add(Calendar.DAY_OF_MONTH, 1) //add a day
        cal[Calendar.HOUR_OF_DAY] = 22 //set hour to last hour
        cal[Calendar.MINUTE] = 40 //set minutes to last minute
        cal[Calendar.SECOND] = 35 //set seconds to last second
        cal[Calendar.MILLISECOND] = 700 //set milliseconds to last millisecond
        val millis2 = cal.timeInMillis

        val event1 = Event(millis1, "o", Color.BLUE)
        val event2 = Event(millis2, "o", Color.RED)

        calenderEvent.addEvent(event1)
        calenderEvent.addEvent(event2)

        calenderEvent.initCalderItemClickCallback { dayContainer ->
            if (dayContainer!!.isHaveEvent) {
                dayContainer.event.time.toString().showToast(this@Reminders)
            } else {
                "You do not have an event on this day".showToast(this@Reminders)
            }
        }
    }

    private fun setTempAdapter() {
        rv_today_reminders.adapter = RemindersAdapter()
        rv_upcoming_reminders.adapter = RemindersAdapter()
        rv_today_reminders.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_upcoming_reminders.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(sl.com.eightdigitz.presentation.R.menu.menu_add_reminder, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            sl.com.eightdigitz.presentation.R.id.add_reminder -> startActivity<AddNewReminder>()
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}