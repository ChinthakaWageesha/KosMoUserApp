package sl.com.eightdigitz.app.presentation.search.recentSearch

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
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DUserSearch
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class RecentSearches : BaseActivity(), View.OnClickListener, (DUser) -> Unit {

    private val vm by viewModel<SearchViewModel>()
    private var searchKey = ""

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
                searchKey = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        vm.liveDataRemoveRecent.observe(this, Observer { observerRemoveRecentSearch(it) })
        vm.liveDataRecentSearches.observe(this, Observer { observerGetRecentSearches(it) })
        getRecentSearches()
        setAdapter(mutableListOf())
        tv_cancel_recent_searches.setOnClickListener(this)
    }

    private fun getRecentSearches() {
        withNetwork({
            vm.getRecentSearches()
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun removeRecentSearchProfile(ownerId: String, searchType: String) {
        withNetwork({
            vm.removeRecentlyViewedProfile(ownerId, searchType)
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }


    private fun observerGetRecentSearches(resource: Resource<List<DUser>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    rv_recent_talent_searches.setEmptyView(tv_no_data_recently_viewed_profiles, it.data!!.size)
                    (rv_recent_talent_searches.adapter as RecentSearchAdapter).addTalentList(it.data!!.toMutableList())

                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(title = Msg.TITLE_ERROR, message = it.message!!)
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
                    setResult(ResultCodes.RECENT_SEARCH_RESULT_CODE)
                    getRecentSearches()
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun setAdapter(recentVisitedProfileList: MutableList<DUser>) {
        rv_recent_talent_searches.adapter = RecentSearchAdapter(recentVisitedProfileList, this)
        rv_recent_talent_searches.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_cancel_recent_searches -> {
                if (searchKey.isNotEmpty()) {
                    et_recent_searches.clearText()
                } else {
                    goBack()
                }
            }
        }
    }

    override fun invoke(talent: DUser) {
        removeRecentSearchProfile(talent.id!!, SearchType.PROFILE_SEARCH)
    }
}