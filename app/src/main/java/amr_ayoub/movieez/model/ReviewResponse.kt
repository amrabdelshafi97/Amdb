package amr_ayoub.movieez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ReviewResponse(val id: Int,
                     val page: Int,
                     val results: List<Review>,
                     val total_pages: Int,
                     val total_results: Int) : Parcelable