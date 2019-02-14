package amr_ayoub.movieez.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import amr_ayoub.movieez.model.Movie

import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface MovieDao {

    @Query("SELECT * from movies")
    fun allMovies(): List<Movie>

    @Query("DELETE from movies")
    fun deleteAllMovies()

    @Query("SELECT * from movies where id Like :id")
    fun checkIfMovieExist(id: Int): List<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

}