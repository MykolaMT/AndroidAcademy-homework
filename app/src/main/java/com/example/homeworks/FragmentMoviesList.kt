package com.example.homeworks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.data.Movie
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {

    private var listener: MovieDetailsListener? = null

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("CoroutineExceptionHandler got $exception in $coroutineContext")
    }

    private var scope = CoroutineScope(
        Dispatchers.Default +
                Job() +
                exceptionHandler
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesView = view.findViewById<RecyclerView>(R.id.movies_view)
        moviesView.adapter = MoviesAdapter(object : MovieDetailsListener {
            override fun openMovie(movie: Movie) {
                listener?.openMovie(movie)
            }
        })

        scope.launch {
            val movies = MoviesDataSource().getMovies(requireContext())

            withContext(Dispatchers.Main) {
                    with(moviesView.adapter as MoviesAdapter) {
                    bindMovies(movies)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieDetailsListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface MovieDetailsListener{
        fun openMovie(movie: Movie)
    }
}

