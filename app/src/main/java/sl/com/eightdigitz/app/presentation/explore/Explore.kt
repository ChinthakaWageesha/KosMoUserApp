package sl.com.eightdigitz.app.presentation.explore

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_explore.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity

class Explore : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        init()
    }

    private fun init(){
        tv_explore_user_name.text = "Rosa Cannon"
        tv_explore_user_field.text = "Athlete"
        iv_close_explore.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_close_explore -> onBackPressed()
        }
    }
}