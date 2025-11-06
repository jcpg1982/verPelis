package pe.com.master.machines.network.utils

object Utils {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZmM2Zjk1NTA5MGE1YTRiMjhhYjEzNTdkNTU5Y2U4MCIsIm5iZiI6MTc2MjM4MTM4Ny44MjksInN1YiI6IjY5MGJjZTRiMzUzMWY2ZjcwZDA3MTM5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EOjmYNMtXvHjo_6-Elmm2j2w6KCjiUkUzSap1uWxnzE"

    object Endpoints {
        const val ALL_MOVIES = "movie/popular"//ALL
        const val SEARCH_MOVIE = "search/movie"//GET
        const val SINGLE_MOVIE = "movie"//GET

    }
}