package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesList.MovieDetailsListener {

    private val fragmentMoviesList =
        FragmentMoviesList().apply { setMovieDetailsListener(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.persistent_container, fragmentMoviesList)
            addToBackStack(null)
            commit()
        }
    }

    override fun openMovie() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.persistent_container, FragmentMoviesDetails())
            addToBackStack(null)
            commit()
        }
    }
}
