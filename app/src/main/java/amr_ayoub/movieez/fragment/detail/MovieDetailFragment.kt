package amr_ayoub.movieez.fragment.detail

import amr_ayoub.movieez.R
import amr_ayoub.movieez.activity.main.detail.MovieDetailActivity
import amr_ayoub.movieez.adapter.ReviewItemViewAdapter
import amr_ayoub.movieez.adapter.SimilarItemRecyclerViewAdapter
import amr_ayoub.movieez.model.Genre
import amr_ayoub.movieez.model.Movie
import amr_ayoub.movieez.model.Review
import amr_ayoub.movieez.model.Trailer
import amr_ayoub.movieez.network.ApiRoute
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_detail_description.view.*
import kotlinx.android.synthetic.main.layout_movie_detail_review.view.*
import kotlinx.android.synthetic.main.layout_movie_detail_similar_movies.view.*
import kotlinx.android.synthetic.main.movie_detail.view.*

/**
 * A fragment representing a single movie detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */

class MovieDetailFragment : Fragment(), MovieDetailContract.View {
    override fun getAppContext(): Context {
        return activity?.applicationContext!!
    }

    private lateinit var mPresenter: MovieDetailContract.Presenter
    private lateinit var mMovie: Movie
    private lateinit var mRootView: View
    private var mMovieIsFav: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = MovieDetailPresenter()
        mPresenter.attach(this)
        setHasOptionsMenu(true)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                mMovie = it.getParcelable(ARG_ITEM_ID)
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                /*item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.toolbar_layout?.title = item?.content*/
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(R.layout.movie_detail, container, false)
        val toolbar = mRootView.findViewById<Toolbar>(R.id.movie_toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_button)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        initUI()
        return mRootView
    }

    private fun setupSimilarMovieRecyclerView(recyclerView: RecyclerView,
                                              parentFragment: MovieDetailFragment) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = SimilarItemRecyclerViewAdapter(parentFragment, emptyList())
        }
    }

    override fun updateView(listReviews: List<Review>, listSimilarMovies: List<Movie>, trailerMovie: Trailer) {
        mMovie.trailer = trailerMovie
        (mRootView.rv_detail_movie_movie_similar_movies.adapter
                as SimilarItemRecyclerViewAdapter).updateData(listSimilarMovies)

        val adapter = ReviewItemViewAdapter(context!!, listReviews)
        mRootView.lv_detail_movie_movie_reviews.adapter = adapter
    }

    override fun initUI() {
        if (mPresenter.checkMovieIsFav(mMovie.id)) {
            mRootView.fab_movie_detail_favourite_button
                    .setImageResource(R.drawable.ic_favorite_white)
            mMovieIsFav = true
        }
        mRootView.movie_toolbar.title = mMovie.original_title
        Picasso.get()
                .load(ApiRoute.BaseBackdropPath + mMovie.backdrop_path)
                .into(mRootView.iv_detail_movie_backdrop_img)
        Picasso.get()
                .load(ApiRoute.BasePosterPath + mMovie.poster_path)
                .into(mRootView.iv_detail_movie_poster_img)
        mRootView.tv_detail_movie_movie_name.text = mMovie.title
        mRootView.tv_detail_movie_movie_release_date.text = (mMovie.release_date!!.year + 1900).toString()
        mRootView.tv_detail_movie_movie_genre.text = getGenres(mMovie.genre_ids)
        mRootView.tv_detail_movie_movie_overview.text = mMovie.overview
        mRootView.tv_detail_movie_movie_rating.text = (mMovie.vote_average / 2).toString()
        mRootView.rb_detail_movie_movie_rating.rating = mMovie.vote_average / 2
        mRootView.fab_movie_detail_favourite_button.setOnClickListener {
            mPresenter.saveMovie(mMovie)
        }
        setupSimilarMovieRecyclerView(mRootView.rv_detail_movie_movie_similar_movies, this)
        mPresenter.getRestOfMovieInfo(mMovie.id)

        mRootView.iv_detail_movie_play_trailer_img.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(ApiRoute.YoutubeBaseUrl + mMovie.trailer?.key)))
        }

        if (mMovieIsFav) {
            mRootView.fab_movie_detail_favourite_button
                    .setImageResource(R.drawable.ic_favorite_white)
            mMovieIsFav = true
        } else {
            mRootView.fab_movie_detail_favourite_button
                    .setImageResource(R.drawable.ic_favorite_border_white_24dp)
            mMovieIsFav = false
        }
        mRootView.fab_movie_detail_favourite_button.setOnClickListener {
            mMovieIsFav = if (mMovieIsFav) {
                mRootView.fab_movie_detail_favourite_button
                        .setImageResource(R.drawable.ic_favorite_border_white_24dp)
                false
            } else {
                mRootView.fab_movie_detail_favourite_button
                        .setImageResource(R.drawable.ic_favorite_white)
                true
            }
        }

    }

    private fun getGenres(genre_ids: List<Int>?): String {
        val genre: MutableList<String> = mutableListOf()
        for (singleGenre in genre_ids!!) {
            genre.add(Genre.genre[singleGenre]!!)
        }
        return genre.joinToString()
    }

    override fun onPause() {
        super.onPause()
        if (mMovieIsFav) {
            mPresenter.saveMovie(mMovie)
        } else {
            mPresenter.removeMovie(mMovie)
        }
    }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
