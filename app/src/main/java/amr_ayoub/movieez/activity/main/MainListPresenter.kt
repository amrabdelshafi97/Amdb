package amr_ayoub.movieez.activity.main

import amr_ayoub.movieez.database.MovieDatabase
import amr_ayoub.movieez.network.GetService
import amr_ayoub.movieez.network.RetrofitInstance
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainListPresenter : MainListContract.Presenter {
    private lateinit var mView: MainListContract.View

    override fun getNowShowingMovies() {
        mView.setTitle("Now Showing")
        val service = RetrofitInstance.getRetrofitInstance().create(GetService::class.java)
        service.getNowShowingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RxKotlin", "Success: " + result.results.size)
                    mView.updateRecyclerView(result.results)
                }, { error ->
                    Log.e("RxKotlin", "Error: " + error.localizedMessage)
                }, {
                    /*onComplete*/
                })
    }

    override fun getTopRatedMovies() {
        mView.setTitle("Top Rated")
        val service = RetrofitInstance.getRetrofitInstance().create(GetService::class.java)
        service.getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RxKotlin", "Success: " + result.results.size)
                    mView.updateRecyclerView(result.results)
                }, { error ->
                    Log.e("RxKotlin", "Error: " + error.localizedMessage)
                })
    }

    override fun getMostPopularMovies() {
        mView.setTitle("Most Popular")
        val service = RetrofitInstance.getRetrofitInstance().create(GetService::class.java)
        service.getMostPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RxKotlin", "Success: " + result.results.size)
                    mView.updateRecyclerView(result.results)
                }, { error ->
                    Log.e("RxKotlin", "Error: " + error.localizedMessage)
                })

    }

    override fun getUpComingMovies() {
        mView.setTitle("Up Coming")
        val service = RetrofitInstance.getRetrofitInstance().create(GetService::class.java)
        service.getUpComingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RxKotlin", "Success: " + result.results.size)
                    mView.updateRecyclerView(result.results)
                }, { error ->
                    Log.e("RxKotlin", "Error: " + error.localizedMessage)
                })
    }

    override fun getFavouriteMovies() {
        mView.setTitle("Favourites")
        val favMovies = MovieDatabase.getAppDatabase(mView.getAppContext())?.userDao()?.allMovies()
        mView.updateRecyclerView(favMovies!!)
    }

    override fun attach(view: MainListContract.View) {
        mView = view
    }
}