package hu.bme.aut.movieapp.ui.main

import androidx.lifecycle.LiveData
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.perstistence.MovieDao
import javax.inject.Inject

class MovieRepository @Inject constructor() {

    fun getAllMovies() : LiveData<List<Movie>>? {
        return null
        //return movieDao.getAllMovies()
    }

    suspend fun delete(movie: Movie) {
        //movieDao.deleteMovie(movie)
    }
}