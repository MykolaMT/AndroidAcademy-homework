package com.example.homeworks

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.example.homeworks.data.Movie
import kotlinx.coroutines.*

class FragmentMovieDetails() : Fragment() {
    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_error)
        .circleCrop()

    private var listener: NavigateBackListener? = null

    private var movieId: Int? = null

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }

    private var scope = CoroutineScope(
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

        var actorsView = view.findViewById<RecyclerView>(R.id.actors_view)
        actorsView.adapter = ActorsAdapter()

        scope.launch {
            val movie = MoviesDataSource().getMovies(requireContext()).single { it.id == movieId }

            renderMovieDetails(view, movie)

            withContext(Dispatchers.Main) {
                with(actorsView.adapter as ActorsAdapter) {
                    bindActors(movie.actors)
                }
            }
        }
    }

    private fun renderMovieDetails(view: View, movie: Movie) {
        with(view) {
            var nameView = findViewById<TextView>(R.id.movie_name)
            var genreView = findViewById<TextView>(R.id.genre)
            var reviewStatsView = findViewById<TextView>(R.id.reviews_stats)
            var storyLineView = findViewById<TextView>(R.id.storyline)

            var ageRatingView = findViewById<TextView>(R.id.age_restriction)
            var detailsPosterView = findViewById<ImageView>(R.id.backdrop_poster)

            nameView.text = movie.title
            genreView.text = movie.genres.joinToString(", ") { it.name }
            reviewStatsView.text = "${movie.numberOfRatings} REVIEWS"
            storyLineView.text = movie.overview
            ageRatingView.text = "${movie.minimumAge}+"

            Glide.with(view.context)
                .load(movie.backdrop)
                .apply(imageOption)
                .into(detailsPosterView)
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

