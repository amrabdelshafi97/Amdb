package amr_ayoub.movieez.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import amr_ayoub.movieez.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun userDao(): MovieDao

    companion object {

        private var INSTANCE: MovieDatabase? = null

        fun getAppDatabase(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "movie-database")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}