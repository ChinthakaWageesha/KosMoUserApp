package sl.com.eightdigitz.app.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import sl.com.eightdigitz.presentation.IntentParsableConstants

class HomeCategoryViewPagerAdapter(
    fragment: Fragment,
    private val categoriesList: MutableList<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = categoriesList.size

    override fun createFragment(position: Int): Fragment {
        val title = categoriesList[position]

        var fragment: Fragment? = null
        val bundle = Bundle()


        fragment = HomeCategoryItem.newInstance()
        bundle.putString(IntentParsableConstants.EXTRA_CATEGORY_ITEM_HOME, title)
        fragment.arguments = bundle

        return fragment
    }
}