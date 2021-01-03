package com.example.homeworks

import android.content.Context
import com.example.homeworks.data.Movie
import com.example.homeworks.data.loadMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDataSource {
    suspend fun getMovies(context: Context): List<Movie> = withContext(Dispatchers.IO) {
        loadMovies(context)
    }
}