package hu.bme.aut.movieapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.movieapp.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE imdbID = :imdbId")
    fun getMovieById(imdbId: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie) : Long

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}