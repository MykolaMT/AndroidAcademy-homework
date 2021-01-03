package com.example.homeworks

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeworks.data.Movie


class MovieViewHolder(itemView: View, var listener: FragmentMoviesList.MovieDetailsListener) : RecyclerView.ViewHolder(itemView) {

    private val nameView = itemView.findViewById<TextView>(R.id.movie_name)
    private val genreView = itemView.findViewById<TextView>(R.id.genre)
    private val lengthView = itemView.findViewById<TextView>(R.id.movie_length)
    private val reviewStatsView = itemView.findViewById<TextView>(R.id.reviews_stats)

    private val ageRatingView = itemView.findViewById<ImageView>(R.id.age_restriction)

    private val reviewLayout = itemView.findViewById<LinearLayout>(R.id.reviews_layout)

    private val starRatingViews = Array<ImageView>(5) {
        var layoutValue = (8 * itemView.context.resources.displayMetrics.density).toInt();
        var layoutParams = ViewGroup.LayoutParams(layoutValue, layoutValue)

        var star = ImageView(itemView.context)
        reviewLayout.addView(star, layoutParams)

        star
    }

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_error)
        .circleCrop()

    fun renderMovieSummary(movie: Movie) {
        nameView.text = movie.title
        genreView.text = movie.genres.joinToString(", ") { it.name }
        reviewStatsView.text = movie.numberOfRatings.toString() + " REVIEWS"

        lengthView.text = movie.runtime.toString()

        val ageRating = if (movie.minimumAge == 13) R.drawable.ic_age_13 else R.drawable.ic_age_16
        ageRatingView.setBackgroundResource(ageRating)

        setStarRating(movie.ratings)

        loadPoster(itemView, movie)

        itemView.setOnClickListener {
            listener.openMovie(movie)
        }
    }

    private fun loadPoster(view: View, movie: Movie) {
        var mainPosterView = view.findViewById<ImageView>(R.id.main_poster)
        Glide.with(view.context)
            .load(movie.poster)
            .apply(imageOption)
            .into(mainPosterView)
    }

    private fun setStarRating(ratings: Float) {
        for (i in 0..4) {
            setStar(starRatingViews[i], ratings >= (i * 2 + 1))
        }
    }

    private fun setStar(starView: ImageView, isFilled: Boolean) {
        if (isFilled)
            starView.setImageResource(R.drawable.ic_star_filled)
        else
            starView.setImageResource(R.drawable.ic_star_empty)
    }
}
