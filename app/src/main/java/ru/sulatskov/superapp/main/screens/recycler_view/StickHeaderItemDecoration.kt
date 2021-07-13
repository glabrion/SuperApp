package ru.sulatskov.superapp.main.screens.recycler_view

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class StickHeaderItemDecoration(
    list: RecyclerView,
    val listener: StickyHeaderInterface
) : RecyclerView.ItemDecoration() {

    private val viewRecycledListener = RecyclerView.RecyclerListener { headers.clear() }
    private val headers = mutableMapOf<Int, RecyclerView.ViewHolder>()
    private var recyclerTouchListener: RecyclerTouchListener
    private var topHeaderPos = -1

    init {
        recyclerTouchListener = RecyclerTouchListener(list, listener)
        list.addOnItemTouchListener(recyclerTouchListener)
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val linearLayoutManager = parent.layoutManager as LinearLayoutManager
        val topChildPosition = linearLayoutManager.findFirstVisibleItemPosition()
        val firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        val lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()

        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }

        val headerPos = listener.getHeaderPositionForItem(topChildPosition)

        if (headerPos == RecyclerView.NO_POSITION) {
            return
        }

        if (topHeaderPos == -1) {
            for (i in 0 until (parent.adapter?.itemCount ?: 0)) {
                linearLayoutManager.findFirstVisibleItemPosition()

                if (listener.isHeader(i)) {
                    topHeaderPos = i

                    break
                }
            }
        }

        val currentHeader = getHeaderViewForItem(headerPos, parent)

        currentHeader?.let {
            if (currentHeader.width != parent.width) {
                fixLayoutSize(parent, currentHeader)
            }
        }

        for (index in 0 until parent.childCount) {
            val child = parent.getChildAt(index)
            val adapterPos = parent.getChildAdapterPosition(child)
            if (adapterPos > -1 && listener.isHeader(adapterPos)) {
                val completedVisible = isCompletedVisible(linearLayoutManager, adapterPos)
                if (!completedVisible || (completedVisible && topChildPosition == adapterPos)) {
                    child.visibility = View.GONE
                } else {
                    child.visibility = View.VISIBLE
                }
            }
        }

        currentHeader?.let {
            val contactPoint = currentHeader.bottom
            val childInContact = getChildInContact(parent, contactPoint)

            if (childInContact != null) {
                if (parent.getChildAdapterPosition(
                        childInContact
                    ) != -1 && listener.isHeader(
                        parent.getChildAdapterPosition(
                            childInContact
                        )
                    )
                ) {
                    moveHeader(c, currentHeader, childInContact, parent)
                    return
                }
            }

            drawHeader(c, currentHeader, parent)
        }
    }

    private fun getHeaderViewForItem(position: Int, parent: RecyclerView): View? {
        if (position == -1) return null
        return parent.adapter?.let { adapter ->
            parent.setRecyclerListener(viewRecycledListener)
            return if (headers.containsKey(position)) {
                headers[position]?.itemView
            } else {
                val header = adapter.createViewHolder(parent, adapter.getItemViewType(position))
                adapter.bindViewHolder(header, position)
                headers[position] = header
                header.itemView
            }
        }
    }

    private fun drawHeader(c: Canvas, header: View, parent: RecyclerView) {
        c.save()
        c.translate(0f, 0f)
        val child = parent.getChildAt(0)
        val adapterPos = parent.getChildAdapterPosition(child)
        recyclerTouchListener.updateSizeView(
            Rect(parent.left, 0, parent.right, header.bottom),
            adapterPos,
            topHeaderPos
        )
        header.draw(c)
        c.restore()
    }

    private fun isCompletedVisible(layoutManager: LinearLayoutManager, position: Int) =
        (layoutManager.findFirstCompletelyVisibleItemPosition()
                ..layoutManager.findLastVisibleItemPosition()).contains(position)

    private fun moveHeader(c: Canvas, currentHeader: View, nextHeader: View, parent: RecyclerView) {
        c.save()
        c.translate(0f, (nextHeader.top - currentHeader.height).toFloat())
        val child = parent.getChildAt(0)
        val adapterPos = parent.getChildAdapterPosition(child)
        recyclerTouchListener.updateSizeView(
            Rect(parent.left, 0, parent.right, currentHeader.bottom - nextHeader.top),
            adapterPos,
            topHeaderPos
        )
        currentHeader.draw(c)
        c.restore()
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (child.bottom > contactPoint) {
                if (child.top <= contactPoint) {
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

    fun invalidate() {
    }

    private fun fixLayoutSize(parent: ViewGroup, view: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec =
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
        val childWidthSpec = ViewGroup.getChildMeasureSpec(
            widthSpec,
            parent.paddingLeft + parent.paddingRight,
            view.layoutParams.width
        )
        val childHeightSpec = ViewGroup.getChildMeasureSpec(
            heightSpec,
            parent.paddingTop + parent.paddingBottom,
            view.layoutParams.height
        )
        view.measure(childWidthSpec, childHeightSpec)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    }

    interface StickyHeaderInterface {
        fun getHeaderPositionForItem(itemPosition: Int): Int
        fun isHeader(itemPosition: Int): Boolean
        fun onClickHeader(childAdapterPosition: Int)
    }

    class RecyclerTouchListener(
        val recyclerView: RecyclerView,
        private val listener: StickyHeaderInterface
    ) : RecyclerView.OnItemTouchListener {

        private var boxRect: Rect = Rect()
        private var childAdapterPosition = 0
        private var startClickTime: Long = 0
        private var firstHeaderPosition: Int = 0

        companion object {
            private const val MAX_CLICK_DURATION = 200
        }

        fun updateSizeView(boxRect: Rect, childAdapterPosition: Int, firstHeaderPosition: Int) {
            this.boxRect = boxRect
            this.childAdapterPosition = childAdapterPosition
            this.firstHeaderPosition = firstHeaderPosition
        }

        override fun onTouchEvent(p0: RecyclerView, p1: MotionEvent) {}

        private fun isHeaderRegion(x: Float, y: Float) =
            x > boxRect.left && x < boxRect.right && y < boxRect.bottom

        override fun onInterceptTouchEvent(rv: RecyclerView, motionEvent: MotionEvent): Boolean {
            if (firstHeaderPosition < rv.getChildAdapterPosition(rv.getChildAt(0))) {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> startClickTime = System.currentTimeMillis()
                    MotionEvent.ACTION_UP ->
                        if (isHeaderRegion(motionEvent.x, motionEvent.y)) {
                            val clickDuration = Calendar.getInstance().timeInMillis - startClickTime

                            if (clickDuration < MAX_CLICK_DURATION) {
                                listener.onClickHeader(rv.getChildAdapterPosition(rv.getChildAt(0)))
                            }

                            return true
                        }
                }
            }

            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {}

    }
}