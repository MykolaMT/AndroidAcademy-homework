package com.example.homeworks

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

fun Array<ImageView>.setStarRating(ratings: Float) {
    for (i in 0 until size) {
        val starView = this[i]

        if (ratings >= (i * 2 + 1))
            starView.setImageResource(R.drawable.ic_star_filled)
        else
            starView.setImageResource(R.drawable.ic_star_empty)
    }
}

class StarRatingHelpers {
    companion object {
        fun createStarView(context: Context, reviewLayout: LinearLayout, layoutValue: Int) : ImageView {
            val star = ImageView(context)

            val layoutValueWithDensity = (layoutValue * context.resources.displayMetrics.density).toInt()
            val layoutParams = ViewGroup.LayoutParams(layoutValueWithDensity, layoutValueWithDensity)

            reviewLayout.addView(star, layoutParams)

            return star
        }
    }
}
