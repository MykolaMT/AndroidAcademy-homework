package com.example.homeworks

class MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie("Avengers: End Game", 13, "Action, Adventure, Drama", 4,
                125, 137, R.drawable.avengers_poster,
                R.drawable.avengers,
                "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                listOf(Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"))
            ),
            Movie("Tenet", 16, "Action, Sci-Fi, Thriller", 5,
                98, 97, R.drawable.tenet_poster,
                R.drawable.tenet_poster,
                "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                listOf(Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"))
            ),
            Movie("Black Widow", 13, "Action, Adventure, Sci-Fi", 4,
                38, 102, R.drawable.blackwidow_poster,
                R.drawable.blackwidow_poster,
                "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.",
                listOf(Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"))
            ),
            Movie("Wonder Woman 1984", 13, "Action, Adventure, Fantasy", 5,
                74, 120, R.drawable.wonderwoman_poster,
                R.drawable.wonderwoman_poster,
                "Fast forward to the 1980s as Wonder Woman's next big screen adventure finds her facing two all-new foes: Max Lord and The Cheetah.",
                listOf(Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"))
            )
        )
    }
}