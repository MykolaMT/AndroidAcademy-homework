package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homeworks.data.Movie

class MainActivity : AppCompatActivity(), FragmentMoviesList.MovieDetailsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.root_container, FragmentMoviesList())
                commit()
            }
        }
    }

    override fun openMovie(movie: Movie) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.persistent_container, FragmentMovieDetails.newInstance(movie.id))
            addToBackStack(null)
            commit()
        }
    }
}
