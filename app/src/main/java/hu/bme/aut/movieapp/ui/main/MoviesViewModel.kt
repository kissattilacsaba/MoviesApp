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

    var dbmode = false

    fun delete(position: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(allMovies.value!![position])
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun search(searchTerm: String) = viewModelScope.launch(Dispatchers.IO) {
        dbmode = false
        repository.searchMovies(allMovies, searchTerm)
    }

    fun getMovies() {
        dbmode = true
        allMovies.addSource(repository.getAllMovies()) {
            if (dbmode)
                allMovies.value = it
        }
    }
}