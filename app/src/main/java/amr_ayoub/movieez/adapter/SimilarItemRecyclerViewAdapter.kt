package amr_ayoub.movieez.adapter

import amr_ayoub.movieez.R
import amr_ayoub.movieez.activity.main.detail.MovieDetailActivity
import amr_ayoub.movieez.fragment.detail.MovieDetailFragment
import amr_ayoub.movieez.model.Genre
import amr_ayoub.movieez.model.Movie
import amr_ayoub.movieez.network.ApiRoute
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_similar_movie.view.*

class SimilarItemRecyclerViewAdapter(private val parentActivity: MovieDetailFragment,
                                     private var values: List<Movie>) :
        RecyclerView.Adapter<SimilarItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private val mSelectedMovie: Movie? = null

    init {
        onClickListener = View.OnClickListener { v ->
            /*if (twoPane) {
                val fragment = MovieDetailFragment().apply {
                    arguments = Bundle().apply {
                        //putString(MovieDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.movie_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, MovieDetailActivity::class.java).apply {
                    //putExtra(MovieDetailFragment.ARG_ITEM_ID, item.id)
                    putExtra("Movie", v.tag as Movie)
                }
                v.context.startActivity(intent)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_similar_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.movieName.text = item.original_title
        Picasso.get()
                .load(ApiRoute.BasePosterPath + item.backdrop_path)
                .into(holder.movieImage)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    private fun getGenres(genre_ids: List<Int>?): String {
        val genre: MutableList<String> = mutableListOf()
        for (singleGenre in genre_ids!!) {
            genre.add(Genre.genre[singleGenre]!!)
        }
        return genre.joinToString()
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName: TextView = view.tv_similar_movie_title
        val movieImage: ImageView = view.iv_similar_movie_poster_img
    }

    fun updateData(newValues: List<Movie>) {
        values = newValues
        notifyDataSetChanged()
    }
}