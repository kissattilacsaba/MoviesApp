package hu.bme.aut.movieapp.perstistence

import androidx.lifecycle.LiveData
import hu.bme.aut.movieapp.model.Movie

interface MovieDao {
    fun getAllMovies(): LiveData<List<Movie>>

    suspend fun insertMovie(movie: Movie) : Long

    suspend fun deleteMovie(movie: Movie)

    suspend fun deleteAllMovies()

}