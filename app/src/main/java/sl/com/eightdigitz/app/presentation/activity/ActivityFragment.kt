package sl.com.eightdigitz.app.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseFragment

class ActivityFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init(){

    }

    companion object{
        const val TAG = "activity"
        fun newInstance(): Fragment{
            return ActivityFragment()
        }
    }
}