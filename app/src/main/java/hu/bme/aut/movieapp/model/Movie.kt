package hu.bme.aut.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey() val imdbID: String,
    val Title: String,
    val Plot: String,
    val Year: String,
    val Director: String,
    val Actors: String,
    val Poster: String,
    val Genre: String,
    val Runtime: String,
    //val imdbID: String,
    val Type: String,
    val Response: String
)
