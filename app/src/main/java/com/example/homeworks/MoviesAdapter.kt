package com.example.homeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.data.Movie

class MoviesAdapter(private val listener: FragmentMoviesList.MovieDetailsListener): RecyclerView.Adapter<MovieViewHolder>() {
    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.renderMovieSummary(movie)
    }

    override fun getItemCount() = movies.size

    fun bindMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}
