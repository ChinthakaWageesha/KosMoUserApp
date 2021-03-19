package sl.com.eightdigitz.app.presentation.reminder

import android.os.Bundle
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity

class AddNewReminder : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_reminder)
    }
}