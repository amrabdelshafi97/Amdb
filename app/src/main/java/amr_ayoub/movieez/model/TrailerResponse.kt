package amr_ayoub.movieez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TrailerResponse(val id: Int, var results: List<Trailer>) : Parcelable