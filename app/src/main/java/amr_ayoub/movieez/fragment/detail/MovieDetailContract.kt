package amr_ayoub.movieez.fragment.detail

import amr_ayoub.movieez.BasePresenter
import amr_ayoub.movieez.model.Movie
import amr_ayoub.movieez.model.Review
import amr_ayoub.movieez.model.Trailer
import android.content.Context

interface MovieDetailContract {
    interface View {
        fun updateView(listReviews: List<Review>,
                       listSimilarMovies: List<Movie>,
                       trailerMovie: Trailer)

        fun initUI()

        fun getAppContext(): Context
    }

    interface Presenter : BasePresenter<View> {
        fun getRestOfMovieInfo(id: Int)
        fun saveMovie(movie: Movie)
        fun getFavouriteMovies(): List<Movie>
        fun checkMovieIsFav(id: Int): Boolean
        fun removeMovie(movie: Movie)
    }
}