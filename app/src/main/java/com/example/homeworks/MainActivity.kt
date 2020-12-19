package com.example.homeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesList.MovieDetailsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.persistent_container, FragmentMoviesList())
                commit()
            }
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
