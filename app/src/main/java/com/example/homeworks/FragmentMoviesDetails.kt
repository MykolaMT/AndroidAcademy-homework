package com.example.homeworks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails(private val movie: Movie) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var nameView = view.findViewById<TextView>(R.id.movie_name)
        var genreView = view.findViewById<TextView>(R.id.genre)
        var reviewStatsView = view.findViewById<TextView>(R.id.reviews_stats)
        var storyLineView = view.findViewById<TextView>(R.id.storyline)

        var ageRatingView = view.findViewById<TextView>(R.id.age_restriction)
        var detailsPosterView = view.findViewById<ImageView>(R.id.main_poster)

        var star1View = view.findViewById<ImageView>(R.id.star_1)
        var star2View = view.findViewById<ImageView>(R.id.star_2)
        var star3View = view.findViewById<ImageView>(R.id.star_3)
        var star4View = view.findViewById<ImageView>(R.id.star_4)
        var star5View = view.findViewById<ImageView>(R.id.star_5)

        nameView.text = movie.name
        genreView.text = movie.genre
        reviewStatsView.text = "${movie.reviewsNumber} REVIEWS"
        storyLineView.text = movie.storyline
        ageRatingView.text = "${movie.age_rating}+"

        detailsPosterView.setBackgroundResource(movie.details_poster)

        SetStar(star1View, movie.stars >= 1)
        SetStar(star2View, movie.stars >= 2)
        SetStar(star3View, movie.stars >= 3)
        SetStar(star4View, movie.stars >= 4)
        SetStar(star5View, movie.stars == 5)
    }

    private fun SetStar(star: ImageView , isFilled: Boolean)
    {
        if(isFilled)
            star.setImageResource(R.drawable.ic_star_filled)
        else
            star.setImageResource(R.drawable.ic_star_empty)
    }
}

