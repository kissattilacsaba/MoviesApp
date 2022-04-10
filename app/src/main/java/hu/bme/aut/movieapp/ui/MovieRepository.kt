package hu.bme.aut.movieapp.ui

import androidx.lifecycle.LiveData
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.perstistence.MovieDao
import javax.inject.Inject

class MovieRepository @Inject constructor() {

    fun getAllMovies() : LiveData<List<Movie>>? {
        return null
        //return movieDao.getAllMovies()
    }

    fun getMovieById(movieId: String) : LiveData<Movie>? {
        return null
    }

    suspend fun insert(movie: Movie) {
        //movieDao.insertMovie(movie)
    }

    suspend fun delete(movie: Movie) {
        //movieDao.deleteMovie(movie)
    }
}