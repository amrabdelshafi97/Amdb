package amr_ayoub.movieez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieResponse(val page: Int,
                    val results: List<Movie>,
                    val total_results: Int,
                    val total_pages: Int) : Parcelable