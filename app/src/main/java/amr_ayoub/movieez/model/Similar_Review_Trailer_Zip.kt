package amr_ayoub.movieez.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Similar_Review_Trailer_Zip(var similarMovies: MovieResponse,
                                 var movieReviews: ReviewResponse,
                                 var movieTrailer: TrailerResponse) : Parcelable