package sl.com.eightdigitz.app.presentation.explore

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_explore.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.makeGone
import sl.com.eightdigitz.presentation.extensions.makeVisible
import sl.com.eightdigitz.presentation.extensions.showToast


class Explore : BaseActivity(), View.OnClickListener {

    private val vmExplore by viewModel<ExploreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        init()
    }

    private fun init() {
        setExploreAdapter()
        vmExplore.getTalents()
        vmExplore.liveDataExplore.observe(this, Observer { observerGetTalents(it) })
        iv_close_explore.setOnClickListener(this)
    }

    private fun observerGetTalents(resource: Resource<List<DUser>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    (rv_explore_user.adapter as ExploreAdapter).clear()
                    if (it.data!!.isNotEmpty()) {
                        cl_explore_base.makeVisible()
                        tv_no_explore_users.makeGone()
                    } else {
                        cl_explore_base.makeGone()
                        tv_no_explore_users.makeVisible()
                    }
                    (rv_explore_user.adapter as ExploreAdapter).addExploreUsers(it.data!!.toMutableList())


                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error?.showToast(this)
                }
            }
        }
    }

    private fun setExploreAdapter() {
        rv_explore_user.adapter = ExploreAdapter(mutableListOf())
        rv_explore_user.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_close_explore -> onBackPressed()
        }
    }




}