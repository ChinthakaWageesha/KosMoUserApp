package sl.com.eightdigitz.presentation.utile

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.presentation.R

class SwipeToDeleteCallback(context: Context, val callback: OnSwipe) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private var background: ColorDrawable = ColorDrawable(Color.RED)
    private var icon: Drawable =
        ContextCompat.getDrawable(context, R.drawable.ic_delete_black_24dp)!!

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        callback.left(position)
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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val backgroundCornerOffset = 20

        val iconMargin = (itemView.height.minus(icon.intrinsicHeight)).div(2)
        val iconTop = itemView.top.plus((itemView.height.minus(icon.intrinsicHeight)).div(2))
        val iconBottom = iconTop.plus(icon.intrinsicHeight)

        if (dX > 0) { // Swiping to the right
            val iconLeft = itemView.left.plus(iconMargin)
            val iconRight = itemView.left.plus(iconMargin).plus(icon.intrinsicWidth)
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)

            background.setBounds(
                itemView.left, itemView.top,
                itemView.left.plus(dX.toInt()).plus(backgroundCornerOffset), itemView.bottom
            )
        } else if (dX < 0) { // Swiping to the left
            val iconLeft = itemView.right.minus(iconMargin).minus(icon.intrinsicWidth)
            val iconRight = itemView.right.minus(iconMargin)
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)

            background.setBounds(
                itemView.right.plus(dX.toInt()).minus(backgroundCornerOffset),
                itemView.top, itemView.right, itemView.bottom
            )
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0)
        }

        if (dX == 0f) {
            background.setBounds(0, 0, 0, 0)
        } else {
            background.draw(c)
            icon.draw(c)
        }
    }
}

interface OnSwipe {
    fun left(position: Int)
}