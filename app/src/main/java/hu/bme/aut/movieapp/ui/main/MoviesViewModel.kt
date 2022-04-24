package hu.bme.aut.movieapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {
    //var allMovies: List<Movie>? = repository.searchMovies()?.Search
    //var allMovies: MutableLiveData<List<Movie>> = repository.getAllMovies()
    var allMovies = MutableLiveData<List<Movie>>(mutableListOf<Movie>())

    fun delete(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(movie)
    }

    fun testApi() {
        //val movies = MutableLiveData<List<Movie>>(repository.searchMovies().Search)
        //allMovies.postValue(repository.searchMovies().Search)
        var movies = listOf<Movie>()

        repository.searchMovies(allMovies)
        Log.v("teszttag", "allMovies: "+allMovies.value?.size.toString())

    }
}