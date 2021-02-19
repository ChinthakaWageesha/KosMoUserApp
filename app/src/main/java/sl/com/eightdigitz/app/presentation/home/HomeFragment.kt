package sl.com.eightdigitz.app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseFragment

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private var categoryList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated() {
        init()
    }

    private fun init(){
        setUpHomeViewPager()
    }

    private fun setUpHomeViewPager(){

        categoryList.add(0,"For you")
        categoryList.add(1,"Editor's Pick")
        categoryList.add(2,"Top Stories")
        categoryList.add(3,"Books")
        categoryList.add(4,"Test category")

        viewpager_categories.adapter = HomeCategoryViewPagerAdapter(
            this,
            categoryList
        )
        viewpager_categories.isUserInputEnabled = false
        TabLayoutMediator(tab_layout, viewpager_categories) { tab, position ->
            val mTabText = categoryList[position]
            tab.text = mTabText
        }.attach()
    }

    companion object {
        const val TAG = "home"
        fun newInstance() = HomeFragment()
    }
}
