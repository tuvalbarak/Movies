package com.example.movies

enum class Category(val category: String, val id: Int) {

    POPULAR("popular", 0),
    UPCOMING("upcoming", 1),
    TOP_RATED("top_rated", 2),
    NOW_PLAYING("now_playing", 3)

}