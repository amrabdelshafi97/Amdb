package amr_ayoub.movieez.network

import amr_ayoub.movieez.model.MovieResponse
import amr_ayoub.movieez.model.ReviewResponse
import amr_ayoub.movieez.model.TrailerResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetService {
    @GET(ApiRoute.MostPopularMovies)
    fun getMostPopularMovies(@Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<MovieResponse>

    @GET(ApiRoute.SimilarMovies)
    fun getSimilarMovies(@Path("movie_id") movieID: String,
                         @Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<MovieResponse>

    @GET(ApiRoute.ReviewsMovies)
    fun getReviewsMovies(@Path("movie_id") movieID: String,
                         @Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<ReviewResponse>

    @GET(ApiRoute.TrailerMovies)
    fun getTrailerMovies(@Path("movie_id") movieID: String,
                         @Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<TrailerResponse>


    @GET(ApiRoute.TopRatedMovies)
    fun getTopRatedMovies(@Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<MovieResponse>

    @GET(ApiRoute.NowShowingMovies)
    fun getNowShowingMovies(@Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<MovieResponse>

    @GET(ApiRoute.UpComingMovies)
    fun getUpComingMovies(@Query("api_key") apiKey: String = ApiRoute.ApiKey)
            : Observable<MovieResponse>
}