package com.example.homeworks

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter(private val context: Context?, private val moviesList: List<Movie>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = moviesList[position]

        val inflator = context!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val movieView = inflator.inflate(R.layout.fragment_movie_summary, null)

        val nameView = movieView.findViewById<TextView>(R.id.movie_name)
        val genreView = movieView.findViewById<TextView>(R.id.genre)
        val lengthView = movieView.findViewById<TextView>(R.id.movie_length)
        val reviewStatsView = movieView.findViewById<TextView>(R.id.reviews_stats)

        val ageRatingView = movieView.findViewById<ImageView>(R.id.age_restriction)
        val summaryPosterView = movieView.findViewById<ImageView>(R.id.main_poster)

        nameView.text = movie.name
        genreView.text = movie.genre
        lengthView.text = "${movie.length} min"
        reviewStatsView.text = "${movie.reviewsNumber} REVIEWS"

        val ageRating = if(movie.age_rating == 13) R.drawable.ic_age_13 else R.drawable.ic_age_16
        ageRatingView.setBackgroundResource(ageRating)

        summaryPosterView.setBackgroundResource(movie.summary_poster)

        SetStar(movieView, R.id.star_1, movie.stars >= 1)
        SetStar(movieView, R.id.star_2, movie.stars >= 2)
        SetStar(movieView, R.id.star_3, movie.stars >= 3)
        SetStar(movieView, R.id.star_4, movie.stars >= 4)
        SetStar(movieView, R.id.star_5, movie.stars == 5)

        return movieView
    }

    private fun SetStar(movieView: View, starViewId: Int , isFilled: Boolean)
    {
        var starView = movieView.findViewById<ImageView>(starViewId)
        if(isFilled)
            starView.setImageResource(R.drawable.ic_star_filled)
        else
            starView.setImageResource(R.drawable.ic_star_empty)
    }

    override fun getItem(position: Int): Any {
        return moviesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return moviesList.count()
    }

}