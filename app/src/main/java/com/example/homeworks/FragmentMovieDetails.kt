package com.example.homeworks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homeworks.data.Movie
import kotlinx.coroutines.*


class FragmentMovieDetails() : Fragment() {

    private var listener: NavigateBackListener? = null

    private var movieId: Int? = null

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }

    private val scope = CoroutineScope(
        Dispatchers.Default +
                Job() +
                exceptionHandler
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movieId = it.getInt(MOVIE_ID_PARAMETER_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actorsView = view.findViewById<RecyclerView>(R.id.actors_view)
        actorsView.adapter = ActorsAdapter()

        scope.launch {
            val movie = MoviesDataSource().getMovies(requireContext()).single { it.id == movieId }

            activity?.runOnUiThread {
                renderMovieDetails(view, movie)
            }

            withContext(Dispatchers.Main) {
                with(actorsView.adapter as ActorsAdapter) {
                    bindActors(movie.actors)
                }
            }
        }
    }

    private fun renderMovieDetails(view: View, movie: Movie) {
        with(view) {
            val nameView = findViewById<TextView>(R.id.movie_name)
            val genreView = findViewById<TextView>(R.id.genre)
            val reviewStatsView = findViewById<TextView>(R.id.reviews_stats)
            val storyLineView = findViewById<TextView>(R.id.storyline)
            val ageRestrictionView = findViewById<TextView>(R.id.age_restriction)
            val detailsPosterView = findViewById<ImageView>(R.id.backdrop_poster)

            val reviewLayout = findViewById<LinearLayout>(R.id.reviews_layout)
            val starRatingViews = Array(5) {
                StarRatingHelpers.createStarView(context,
                    reviewLayout,
                    8)
            }

            detailsPosterView.load(movie.backdrop)

            nameView.text = movie.title
            genreView.text = movie.genres.joinToString(", ") { it.name }
            storyLineView.text = movie.overview

            reviewStatsView.text = getString(R.string.review_number, movie.numberOfRatings)
            ageRestrictionView.text = getString(R.string.minimal_age, movie.minimumAge)

            starRatingViews.setStarRating(movie.ratings)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigateBackListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface NavigateBackListener {
        fun back()
    }

    companion object {
        const val MOVIE_ID_PARAMETER_NAME = "movieId"

        fun newInstance(movieId: Int): FragmentMovieDetails {
            val fragmentMovieDetails = FragmentMovieDetails()
            val args = Bundle()
            args.putInt(MOVIE_ID_PARAMETER_NAME, movieId)
            fragmentMovieDetails.arguments = args
            return fragmentMovieDetails
        }
    }
}

