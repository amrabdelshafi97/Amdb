package amr_ayoub.movieez.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import java.util.*
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "movies")
data class Movie(@PrimaryKey var id: Int = 0,
                 var poster_path: String = "",
                 var overview: String = "",
                 var title: String = "",
                 var backdrop_path: String = "",
                 var vote_average: Float = 0.0F,
                 @Ignore var genre_ids: List<Int> = emptyList(),
                 @Ignore var adult: Boolean = false,
                 @Ignore var release_date: Date? = null,
                 @Ignore var original_title: String = "",
                 @Ignore var trailer: Trailer? = null)
    : Parcelable