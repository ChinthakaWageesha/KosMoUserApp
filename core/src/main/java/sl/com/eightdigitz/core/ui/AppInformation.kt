package sl.com.eightdigitz.core.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setActionBar

class AppInformation : BaseActivity() {

    companion object{
        fun startActivity(context: Context, action: String){
            val intent = Intent(context, AppInformation::class.java).apply {
                this.action = action
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_informations)
        setToolbar()
    }

    private fun setToolbar(){
        supportActionBar?.setActionBar(
            this,
            intent.action!!,
            isHomeUpEnables = true,
            isCenterTitle = true
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
