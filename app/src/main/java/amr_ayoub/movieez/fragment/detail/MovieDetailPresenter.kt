package amr_ayoub.movieez.fragment.detail

import amr_ayoub.movieez.database.MovieDatabase
import amr_ayoub.movieez.model.*
import amr_ayoub.movieez.network.GetService
import amr_ayoub.movieez.network.RetrofitInstance
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class MovieDetailPresenter : MovieDetailContract.Presenter {
    override fun removeMovie(movie: Movie) {
        MovieDatabase.getAppDatabase(mView.getAppContext())!!.userDao().delete(movie)
    }

    override fun checkMovieIsFav(id: Int): Boolean {
        val db = MovieDatabase.getAppDatabase(mView.getAppContext())
        val mMovie = db?.userDao()?.checkIfMovieExist(id)
        return mMovie != null
    }

    override fun saveMovie(movie: Movie) {
        val db = MovieDatabase.getAppDatabase(mView.getAppContext())
        db?.userDao()?.insert(movie)
    }

    override fun getFavouriteMovies(): List<Movie> {
        return MovieDatabase.getAppDatabase(mView.getAppContext())?.userDao()?.allMovies()!!
    }

    private lateinit var mView: MovieDetailContract.View

    override fun getRestOfMovieInfo(id: Int) {
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(GetService::class.java)
        Observable.zip(
                retrofitService.getSimilarMovies(id.toString()),
                retrofitService.getReviewsMovies(id.toString()),
                retrofitService.getTrailerMovies(id.toString()),
                Function3<MovieResponse, ReviewResponse, TrailerResponse,
                        Similar_Review_Trailer_Zip> { movieResponse: MovieResponse,
                                                      reviewResponse: ReviewResponse,
                                                      trailerResponse: TrailerResponse ->
                    return@Function3 Similar_Review_Trailer_Zip(movieResponse, reviewResponse, trailerResponse)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.i("RxKotlin", "SuccessDetail: $result")
                    mView.updateView(result.movieReviews.results, result.similarMovies.results, result.movieTrailer.results[0])
                }, { error ->
                    Log.e("RxKotlin", "ErrorDetail: " + error.message)
                })

        /*var similar_Review_Trailer_Zip: Similar_Review_Trailer_Zip? = null
        retrofitService.getSimilarMovies(movieID.toString())
                .flatMap {
                    similar_Review_Trailer_Zip?.similarMovies = it
                    Log.i("RxKotlin", "SuccessDetail: Similar Movie Size" + it.results.size)
                    return@flatMap retrofitService.getReviewsMovies(movieID.toString())
                }
                .flatMap {
                    Log.i("RxKotlin", "SuccessDetail: Similar review Size" + it.results.size)
                    similar_Review_Trailer_Zip?.movieReviews = it
                    return@flatMap retrofitService.getTrailerMovies(movieID.toString())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    similar_Review_Trailer_Zip?.movieTrailer = it
                    Log.i("RxKotlin", "SuccessDetail: Similar trailer Size" + it.results.size)
                    mView.updateView(similar_Review_Trailer_Zip!!.movieReviews.results,
                            similar_Review_Trailer_Zip!!.similarMovies.results,
                            similar_Review_Trailer_Zip!!.movieTrailer.results[0])
                }*/
    }

    override fun attach(view: MovieDetailContract.View) {
        mView = view
    }
}