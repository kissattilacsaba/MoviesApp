package hu.bme.aut.movieapp.model

data class DetailedMovie (
    val Title: String,
    val Plot: String
    /*val Year: String,
    val Rated: String,
    val Released: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language: String,
    val Country: String,
    val Awards: String,
    val Poster: String,
    val Ratings: List<Rating>,
    val Metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    val DVD: String,
    val BoxOffice: String,
    val Production: String,
    val Website: String,
    val Response: String*/
)

class Rating (
    val Source: String,
    val Value: String
)
