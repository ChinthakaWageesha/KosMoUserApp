package sl.com.eightdigitz.core.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_app_informations.*
import sl.com.eightdigitz.core.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.setActionBar

class AppInformation : BaseActivity(), View.OnClickListener {

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
        iv_back_app_info.setOnClickListener(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.iv_back_app_info -> {
               onBackPressed()
           }
       }
    }
}
