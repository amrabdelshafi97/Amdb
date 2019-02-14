package amr_ayoub.movieez.activity.main

import amr_ayoub.movieez.BasePresenter
import amr_ayoub.movieez.model.Movie
import android.content.Context

interface MainListContract {
    interface View {
        fun updateRecyclerView(list: List<Movie>)
        fun setTitle(title: String)
        fun getAppContext(): Context

    }

    interface Presenter : BasePresenter<View> {
        fun getTopRatedMovies()
        fun getMostPopularMovies()
        fun getNowShowingMovies()
        fun getUpComingMovies()
        fun getFavouriteMovies()
    }
}