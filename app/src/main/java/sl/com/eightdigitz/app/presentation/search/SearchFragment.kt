package sl.com.eightdigitz.app.presentation.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.search.preferenceSearch.SearchTalentByPreference
import sl.com.eightdigitz.app.presentation.search.recentSearch.RecentSearches
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class SearchFragment : BaseFragment(), (DPreference) -> Unit, View.OnClickListener {

    private val vm by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        setRecentSearchAdapter(mutableListOf())
        getSearchCategories()
        setPeopleYouKnowAdapter()
        setRecommendedAdapter()
        et_search_talent_home.setOnClickListener(this)
        vm.liveDataCategories.observe(this, Observer { observerGetCategories(it) })
        vm.liveDataRecentSearches.observe(this, Observer { observerGetRecentSearches(it) })
    }

    private fun getSearchCategories() {
        activity?.withNetwork({
            vm.getSearchCategories()
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun getRecentSearches() {
        activity?.withNetwork({
            vm.getRecentSearches()
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun setUpPreferenceAdapter(preferenceList: MutableList<DPreference>) {
        rv_preferences_home.adapter = PreferenceAdapter(preferenceList, this)
        rv_preferences_home.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setRecentSearchAdapter(recentTalentList: MutableList<DUser>) {
        rv_recent_searches.adapter = SearchAdapterTalents(recentTalentList)
        rv_recent_searches.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setPeopleYouKnowAdapter() {
        rv_people_you_know.adapter = TempAdapter(Constant.USER_IMAGE_AQUAMAN)
        rv_people_you_know.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setRecommendedAdapter() {
        rv_recommended.adapter = TempAdapter(Constant.USER_IMAGE_TOM_HARDY)
        rv_recommended.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observerGetCategories(resource: Resource<List<DPreference>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setUpPreferenceAdapter(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(context!!)
                }
            }
        }
    }

    private fun observerGetRecentSearches(resource: Resource<List<DUser>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    rv_recent_searches.setEmptyView(tv_no_data_recent_searches, it.data!!.size)
                    (rv_recent_searches.adapter as SearchAdapterTalents).clear()
                    (rv_recent_searches.adapter as SearchAdapterTalents).addTalents(it.data!!.toMutableList())

                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.message?.error.toString().showToast(context!!)
                }
            }
        }
    }

    companion object {
        const val TAG = "search"

        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun invoke(preference: DPreference) {
        val intent = Intent(context!!, SearchTalentByPreference::class.java)
        intent.putExtra(IntentParsableConstants.EXTRA_PREFERENCE, preference)
        startActivity(intent)
    }

    override fun onResume() {
        hideKeyboard()
        getRecentSearches()
        super.onResume()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.et_search_talent_home -> {
                context?.startActivity<RecentSearches>()
            }
        }
    }
}