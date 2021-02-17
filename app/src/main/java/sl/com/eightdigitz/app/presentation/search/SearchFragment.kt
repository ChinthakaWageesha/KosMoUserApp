package sl.com.eightdigitz.app.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.presentation.extensions.showToast

class SearchFragment : BaseFragment(), (String) -> Unit {

    private var preferenceList = mutableListOf<String>()

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
        setUpPreferenceAdapter()
        setRecentSearchAdapter()
        setPeopleYouKnowAdapter()
        setRecommendedAdapter()
    }

    private fun setUpPreferenceAdapter() {
        preferenceList.add(0, "Cricket")
        preferenceList.add(1, "Movie")
        preferenceList.add(2, "Music")
        preferenceList.add(3, "Business")
        preferenceList.add(4, "Politics")
        preferenceList.add(5, "Transport")
        preferenceList.add(6, "Security")

        rv_preferences_home.adapter = PreferenceAdapter(preferenceList, this)
        rv_preferences_home.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setRecentSearchAdapter() {
        rv_recent_searches.adapter = PeopleSearchAdapter()
        rv_recent_searches.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setPeopleYouKnowAdapter() {
        rv_people_you_know.adapter = PeopleSearchAdapter()
        rv_people_you_know.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setRecommendedAdapter() {
        rv_recommended.adapter = PeopleSearchAdapter()
        rv_recommended.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        const val TAG = "search"

        fun newInstance(): Fragment {
            return SearchFragment()
        }
    }

    override fun invoke(preference: String) {
        preference.showToast(context!!)
    }
}