package amr_ayoub.movieez.activity.main.main


import amr_ayoub.movieez.R
import amr_ayoub.movieez.activity.main.MainListContract
import amr_ayoub.movieez.activity.main.MainListPresenter
import amr_ayoub.movieez.adapter.SimpleItemRecyclerViewAdapter
import amr_ayoub.movieez.model.Movie
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.movie_list.*


class MainListActivity : AppCompatActivity(), MainListContract.View {
    override fun setTitle(title: String) {
        tv_activity_movie_list_toolbar_title.text = title
    }

    override fun updateRecyclerView(list: List<Movie>) {
        (movie_list.adapter as SimpleItemRecyclerViewAdapter).updateData(list)
    }

    private var twoPane: Boolean = false
    private lateinit var mPresenter: MainListContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list)
        mPresenter = MainListPresenter()
        mPresenter.attach(this)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        if (movie_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(movie_list, this)
        mPresenter.getTopRatedMovies()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, parentActivity: MainListActivity) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SimpleItemRecyclerViewAdapter(parentActivity, emptyList(), twoPane)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorites -> {
            mPresenter.getFavouriteMovies()
            true
        }
        R.id.action_top_rated -> {
            mPresenter.getTopRatedMovies()
            true
        }
        R.id.action_most_popular -> {
            mPresenter.getMostPopularMovies()
            true
        }
        R.id.action_now_showing -> {
            mPresenter.getNowShowingMovies()
            true
        }
        R.id.action_upcoming -> {
            mPresenter.getUpComingMovies()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun getAppContext(): Context {
        return this.applicationContext
    }

}
