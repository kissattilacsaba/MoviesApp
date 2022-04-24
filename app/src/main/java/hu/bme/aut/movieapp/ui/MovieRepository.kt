package hu.bme.aut.movieapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.movieapp.model.DetailedMovie
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.model.SearchResult
import hu.bme.aut.movieapp.network.MovieService
import hu.bme.aut.movieapp.perstistence.MovieDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    fun searchMovies(allMovies: MutableLiveData<List<Movie>>) {
        val call = movieService.getMovies("jurassic", "531f73d8")
        call.enqueue(object: Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                allMovies.postValue((response.body()!!.Search))
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                t.message?.let { Log.e("Test", it) }
            }
        })
    }

    /*fun getAllMovies() : MutableLiveData<List<Movie>> {
        return mutableListOf<Movie>()
        //return movieDao.getAllMovies()
    }*/

    fun getMovieById(movieId: String, movie: MutableLiveData<DetailedMovie>) {
        val call = movieService.getSingleMovie(movieId, "531f73d8")
        call.enqueue(object: Callback<DetailedMovie>{
            override fun onResponse(call: Call<DetailedMovie>, response: Response<DetailedMovie>) {
                    movie.postValue(response.body())
            }

            override fun onFailure(call: Call<DetailedMovie>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    suspend fun insert(movie: Movie) {
        //movieDao.insertMovie(movie)
    }

    suspend fun delete(movie: Movie) {
        //movieDao.deleteMovie(movie)
    }


}