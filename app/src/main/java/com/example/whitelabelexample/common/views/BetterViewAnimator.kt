package com.example.whitelabelexample.common.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewAnimator
import com.example.whitelabelexample.R

open class BetterViewAnimator @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewAnimator(context, attrs) {

    init {
        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(this,
                    R.styleable.BetterViewAnimator, 0, 0)
            if (typedArray.hasValue(R.styleable.BetterViewAnimator_visible_child)) {
                visibleChildId = typedArray
                        .getResourceId(R.styleable.BetterViewAnimator_visible_child, 0)
            }
            typedArray.recycle()
        }
    }

    var visibleChildId: Int
        get() = getChildAt(displayedChild).id
        set(id) {
            if (visibleChildId == id) {
                return
            }
            var i = 0
            val count = childCount
            while (i < count) {
                if (getChildAt(i).id == id) {
                    displayedChild = i
                    return
                }
                i++
            }
            throw IllegalArgumentException("No view with ID $id")
        }

}
