package hu.bme.aut.movieapp.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val repository: MovieRepository) : ViewModel() {

    var allMovies = MediatorLiveData<List<Movie>>()

    fun delete(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(movie)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun search(searchTerm: String) {
        repository.searchMovies(allMovies, searchTerm)
    }

    fun getMovies() {
        allMovies.addSource(repository.getAllMovies()) {
            allMovies.value = it
        }
    }
}