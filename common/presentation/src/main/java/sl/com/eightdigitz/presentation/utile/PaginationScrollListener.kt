package sl.com.eightdigitz.presentation.utile

import android.util.Log
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.presentation.Constant

object TempVar {
    var per_page: Int = 10
}

public abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(
        recyclerView: RecyclerView,
        dx: Int,
        dy: Int
    ) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= Constant.PERPAGE) {
                loadMoreItems()
            }
        }
        onScroll()
    }

    override fun onScrollStateChanged(
        recyclerView: RecyclerView,
        newState: Int
    ) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    open fun onScroll() {}
}

public abstract class PaginationScrollViewListener(private val layoutManager: LinearLayoutManager) :
    NestedScrollView.OnScrollChangeListener {

    override fun onScrollChange(
        v: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        Log.e("Scroll", "" + v?.canScrollVertically(1))
        if (!v?.canScrollVertically(1)!!) {
            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= Constant.PERPAGE) {
                    loadMoreItems()
                }
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}