package sl.com.eightdigitz.app.presentation.search.recentSearch

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recent_searches.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.search.SearchViewModel
import sl.com.eightdigitz.app.presentation.profile.ViewProfile
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class RecentSearches : BaseActivity(), View.OnClickListener, (DUser, Int) -> Unit {

    private val vmSearch by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_searches)
        init()
    }

    private fun init() {
        et_recent_searches.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (sequence.isNullOrEmpty()) {
                    clear()
                    getRecentSearches()
                } else {
                    clear()
                    vmSearch.searchKeyTalents = et_recent_searches.getStringTrim()
                    getTalents()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        setRecentTalentAdapter()
        setTalentAdapter()
        vmSearch.liveDataRemoveRecent.observe(this, Observer { observerRemoveRecentSearch(it) })
        vmSearch.liveDataRecentSearches.observe(this, Observer { observerGetRecentSearches(it) })
        vmSearch.liveDataTalents.observe(this, Observer { observerGetTalents(it) })
        getRecentSearches()
        tv_cancel_recent_searches.setOnClickListener(this)
    }

    private fun setRecentTalentAdapter() {
        rv_recent_talent_searches.adapter = RecentSearchAdapter(ViewTypes.RECENT_TALENTS, this)
        rv_recent_talent_searches.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setTalentAdapter() {
        rv_recent_talent_searches.adapter = RecentSearchAdapter(ViewTypes.TALENTS, this)
        rv_recent_talent_searches.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    @SuppressLint("MissingPermission")
    private fun getRecentSearches() {
        setViewType(ViewTypes.RECENT_TALENTS)
        tv_title_recent_searches.text = getString(sl.com.eightdigitz.presentation.R.string.title_recent_searches)
        withNetwork({
            vmSearch.getRecentSearches()
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    @SuppressLint("MissingPermission")
    private fun getTalents() {
        setViewType(ViewTypes.TALENTS)
        tv_title_recent_searches.text = getString(sl.com.eightdigitz.presentation.R.string.title_talent_searches)
        withNetwork({
            vmSearch.getTalents()
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun setViewType(type: Int) {
        (rv_recent_talent_searches.adapter as RecentSearchAdapter).viewType = type
        (rv_recent_talent_searches.adapter as RecentSearchAdapter).notifyDataSetChanged()
    }

    @SuppressLint("MissingPermission")
    private fun removeRecentSearchProfile(ownerId: String, searchType: String) {
        withNetwork({
            vmSearch.removeRecentlyViewedProfile(ownerId, searchType)
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun clear() {
        (rv_recent_talent_searches.adapter as RecentSearchAdapter).clear()
    }


    private fun observerGetRecentSearches(resource: Resource<List<DUser>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    clear()
                    rv_recent_talent_searches.setEmptyView(
                        tv_no_data_talents,
                        it.data!!.size
                    )
                    (rv_recent_talent_searches.adapter as RecentSearchAdapter).addTalentList(it.data!!.toMutableList())

                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerGetTalents(resource: Resource<List<DUser>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    clear()
                    rv_recent_talent_searches.setEmptyView(
                        tv_no_data_talents,
                        it.data!!.size
                    )
                    (rv_recent_talent_searches.adapter as RecentSearchAdapter).addTalentList(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    private fun observerRemoveRecentSearch(resource: Resource<DUserSearch>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    getRecentSearches()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(this)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_cancel_recent_searches -> {
                if (et_recent_searches.text.isNotEmpty()) {
                    et_recent_searches.clearText()
                    clear()
                    getRecentSearches()
                } else {
                    hideKeyboard()
                    onBackPressed()
                }
            }
        }
    }

    override fun invoke(talent: DUser, type: Int) {
        if (type == ViewTypes.RECENT_TALENTS){
            removeRecentSearchProfile(talent.id!!, SearchType.PROFILE_SEARCH)
        } else if (type == ViewTypes.TALENTS){
            val intent = Intent(this, ViewProfile::class.java)
            intent.putExtra(IntentParsableConstants.EXTRA_USER, talent)
            startActivity(intent)
        }

    }
}

