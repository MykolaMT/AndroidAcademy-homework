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

        var inflator = context!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var movieView = inflator.inflate(R.layout.fragment_movie_summary, null)

        var nameView = movieView.findViewById<TextView>(R.id.movie_name)
        var genreView = movieView.findViewById<TextView>(R.id.genre)
        var lengthView = movieView.findViewById<TextView>(R.id.movie_length)
        var reviewStatsView = movieView.findViewById<TextView>(R.id.reviews_stats)

        var ageRatingView = movieView.findViewById<ImageView>(R.id.age_restriction)
        var star1View = movieView.findViewById<ImageView>(R.id.star_1)
        var star2View = movieView.findViewById<ImageView>(R.id.star_2)
        var star3View = movieView.findViewById<ImageView>(R.id.star_3)
        var star4View = movieView.findViewById<ImageView>(R.id.star_4)
        var star5View = movieView.findViewById<ImageView>(R.id.star_5)
        var summaryPosterView = movieView.findViewById<ImageView>(R.id.main_poster)

        nameView.text = movie.name
        genreView.text = movie.genre
        lengthView.text = "${movie.length} min"
        reviewStatsView.text = "${movie.reviewsNumber} REVIEWS"

        var ageRating = if(movie.age_rating == 13) R.drawable.ic_age_13 else R.drawable.ic_age_16
        ageRatingView.setBackgroundResource(ageRating)

        summaryPosterView.setBackgroundResource(movie.summary_poster)

        SetStar(star1View, movie.stars >= 1)
        SetStar(star2View, movie.stars >= 2)
        SetStar(star3View, movie.stars >= 3)
        SetStar(star4View, movie.stars >= 4)
        SetStar(star5View, movie.stars == 5)

        return movieView
    }

    private fun SetStar(star: ImageView , isFilled: Boolean)
    {
        if(isFilled)
            star.setImageResource(R.drawable.ic_star_filled)
        else
            star.setImageResource(R.drawable.ic_star_empty)
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