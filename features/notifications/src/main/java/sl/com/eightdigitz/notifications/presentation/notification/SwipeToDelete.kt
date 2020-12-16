package sl.com.eightdigitz.notifications.presentation.notification

import android.content.Context
import android.graphics.*
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.notifications.R

class SwipeToDelete(val context: Context?, val callback: Callback) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val p = Paint()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition

        if (direction == ItemTouchHelper.LEFT) {
            callback.onSwipeLeft(position)
        } else {
            callback.onSwipeRight(position)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val icon: Bitmap
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            val itemView = viewHolder.itemView
            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
            val width = height / 3
            val drawable = context!!.resources.getDrawable(R.drawable.ic_delete)
            icon = drawable.toBitmap()
            if (dX > 0) {
                p.color = Color.parseColor("#388E3C")
                val background =
                    RectF(
                        itemView.left.toFloat(),
                        itemView.top.toFloat(),
                        dX,
                        itemView.bottom.toFloat()
                    )
                c.drawRect(background, p)

                val icon_dest = RectF(
                    itemView.left.toFloat() + width,
                    itemView.top.toFloat() + width,
                    itemView.left.toFloat() + 2 * width,
                    itemView.bottom.toFloat() - width
                )
                c.drawBitmap(icon, null, icon_dest, p)
            } else {
                p.color = Color.parseColor("#D32F2F")
                val background = RectF(
                    itemView.right.toFloat() + dX,
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat()
                )
                c.drawRect(background, p)
                val iconDest = RectF(
                    itemView.right.toFloat() - 2 * width,
                    itemView.top.toFloat() + width,
                    itemView.right.toFloat() - width,
                    itemView.bottom.toFloat() - width
                )
                c.drawBitmap(icon, null, iconDest, p)
            }
        }
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    interface Callback {
        fun onSwipeRight(position: Int)
        fun onSwipeLeft(position: Int)
    }
}
