package amr_ayoub.movieez.activity.main.detail

import amr_ayoub.movieez.R
    import amr_ayoub.movieez.fragment.detail.MovieDetailFragment
import amr_ayoub.movieez.model.Movie
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mMovieData: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        mMovieData = intent.getParcelableExtra("Movie")


        val fragment = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MovieDetailFragment.ARG_ITEM_ID, mMovieData)
            }
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.movie_detail_container, fragment)
                .commit()
        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        /*if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(MovieDetailFragment.ARG_ITEM_ID,
                            intent.getStringExtra(MovieDetailFragment.ARG_ITEM_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit()
        }*/
    }



}
