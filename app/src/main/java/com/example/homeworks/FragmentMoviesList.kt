package com.example.homeworks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment() {

    private var listener: MovieDetailsListener? = null
    private var movies: List<Movie> = MoviesDataSource().getMovies()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val grid = view.findViewById<GridView>(R.id.movies_summary)
        grid.adapter = MovieAdapter(context, movies)

        grid.setOnItemClickListener { _, _, position, _ ->
            listener?.openMovie(movies[position])
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
