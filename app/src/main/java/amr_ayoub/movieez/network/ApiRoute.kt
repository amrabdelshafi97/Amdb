package amr_ayoub.movieez.network

object ApiRoute {

    const val ApiKey = "107ed75bf9e25ec06bfe9fd33d042579"

    const val BasePosterPath = "https://image.tmdb.org/t/p/w500"
    const val BaseBackdropPath = "https://image.tmdb.org/t/p/w780"
    const val BaseURL = "http://api.themoviedb.org/3/movie/"

    const val TopRatedMovies = "top_rated"
    const val MostPopularMovies = "popular"
    const val NowShowingMovies = "now_playing"
    const val UpComingMovies = "upcoming"

    const val SimilarMovies = "{movie_id}/similar"
    const val ReviewsMovies = "{movie_id}/reviews"
    const val TrailerMovies = "{movie_id}/videos"

    const val YoutubeBaseUrl = "https://www.youtube.com/watch?v="

}