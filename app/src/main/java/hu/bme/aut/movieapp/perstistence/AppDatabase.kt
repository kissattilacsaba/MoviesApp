package hu.bme.aut.movieapp.perstistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.movieapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase()  {

    abstract fun movieDao(): MovieDao

}