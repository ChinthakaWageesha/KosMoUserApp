package sl.com.eightdigitz.app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import kotlinx.android.synthetic.main.fragment_home_category_item.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.home.adapters.HomeLatestNewsAdapter
import sl.com.eightdigitz.app.presentation.home.adapters.HomeMainAdapter
import sl.com.eightdigitz.app.presentation.home.adapters.HomeMostPopularAdapter
import sl.com.eightdigitz.core.base.BaseFragment


class HomeCategoryItem : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_category_item, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        setLatestNewsAdapter()
        setMostPopularAdapter()
        setMainAdapter()
    }

    private fun setLatestNewsAdapter() {
        rv_latest_news.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        rv_latest_news.adapter = HomeLatestNewsAdapter()
    }

    private fun setMostPopularAdapter() {
        rv_most_popular.layoutManager = GridLayoutManager(context!!, 2)
        rv_most_popular.adapter = HomeMostPopularAdapter()
    }

    private fun setMainAdapter() {
        val snapHelper = PagerSnapHelper()
        rv_home_main.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.HORIZONTAL, false)
        snapHelper.attachToRecyclerView(rv_home_main)

        rv_home_main.post { rv_home_main.adapter = HomeMainAdapter(rv_home_main) }

    }

    companion object {
        const val TAG = "home_category_item"

        fun newInstance(): Fragment {
            return HomeCategoryItem()
        }
    }
}