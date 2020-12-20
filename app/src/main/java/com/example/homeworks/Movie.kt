package com.example.homeworks

data class Movie (
    val name: String,
    val age_rating: Int,
    val genre: String,
    val stars: Int,
    val reviewsNumber: Int,
    val length: Int,
    val summary_poster: Int,
    val details_poster: Int,
    val storyline: String,
    val cast: List<Actor>
)

data class Actor (
    val name: String,
    val picture: String
)
