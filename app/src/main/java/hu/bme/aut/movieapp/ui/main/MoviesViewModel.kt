package hu.bme.aut.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.movieapp.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {
    var allMovies: LiveData<List<Movie>>? = null

    init {
        //val moviesDao = AppDatabase.getInstance(application).movieDao()
        //repository = MovieRepository(moviesDao)
        //allMovies = repository.getAllMovies()
    }

    fun delete(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(movie)
    }
}