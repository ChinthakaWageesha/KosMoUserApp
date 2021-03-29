package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseFragment

class RejectedFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_rejected, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init(){

    }

    companion object {

        fun newInstance(): Fragment {
            return RejectedFragment()
        }
    }

}