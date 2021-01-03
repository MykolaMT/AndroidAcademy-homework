package com.example.homeworks

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.request.RequestOptions
import com.example.homeworks.data.Movie


class MovieViewHolder(itemView: View, var listener: FragmentMoviesList.MovieDetailsListener) : RecyclerView.ViewHolder(
    itemView) {

    private val reviewLayout = itemView.findViewById<LinearLayout>(R.id.reviews_layout)

    private val nameView = itemView.findViewById<TextView>(R.id.movie_name)
    private val genreView = itemView.findViewById<TextView>(R.id.genre)
    private val lengthView = itemView.findViewById<TextView>(R.id.movie_length)
    private val reviewStatsView = itemView.findViewById<TextView>(R.id.reviews_stats)

    private val ageRatingView = itemView.findViewById<ImageView>(R.id.age_restriction)
    private val mainPosterView = itemView.findViewById<ImageView>(R.id.main_poster)

    private val starRatingViews = Array(5) { StarRatingHelpers.createStarView(itemView.context, reviewLayout, 8) }

    fun renderMovieSummary(movie: Movie) {
        nameView.text = movie.title
        genreView.text = movie.genres.joinToString(", ") { it.name }

        val resources = itemView.context.resources
        reviewStatsView.text = resources.getString(R.string.review_number, movie.numberOfRatings)
        lengthView.text = resources.getString(R.string.runtime, movie.runtime)

        val ageRating = if (movie.minimumAge == 13) R.drawable.ic_age_13 else R.drawable.ic_age_16
        ageRatingView.setBackgroundResource(ageRating)

        starRatingViews.setStarRating(movie.ratings)

        mainPosterView.load(movie.poster)

        itemView.setOnClickListener {
            listener.openMovie(movie)
        }
    }
}






