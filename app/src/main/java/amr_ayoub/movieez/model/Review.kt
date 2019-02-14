package amr_ayoub.movieez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Review(val author: String,
             val content: String) : Parcelable