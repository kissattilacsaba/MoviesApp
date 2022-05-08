package hu.bme.aut.movieapp.model

 data class SearchResult (
     val Search: List<Movie>,
     val totalResults: Int,
     val Response: String
)