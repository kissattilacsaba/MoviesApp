package hu.bme.aut.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.network.MovieService
import hu.bme.aut.movieapp.persistence.MovieDao
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) {

    suspend fun searchMovies(allMovies: MutableLiveData<List<Movie>>, searchTerm: String) {
        val call = movieService.getMovies(searchTerm, "531f73d8")
        val movies = call.body()!!.Search
        allMovies.postValue(movies)
    }

    fun getAllMovies(): LiveData<List<Movie>> {
        return movieDao.getAllMovies()
    }

    suspend fun getMovieById(movieId: String, movie: MutableLiveData<Movie>) {
        val movies = movieDao.getMovieById(movieId)
        if (movies.isNotEmpty())
            movie.postValue(movies[0])
        else {
            val call = movieService.getSingleMovie(movieId, "531f73d8")
            val response = call.body()!!
            movie.postValue(response)
        }
    }

    suspend fun insert(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    suspend fun delete(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    suspend fun deleteAll() {
        movieDao.deleteAllMovies()
    }


}