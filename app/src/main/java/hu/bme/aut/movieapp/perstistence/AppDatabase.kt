package hu.bme.aut.movieapp.perstistence

abstract class AppDatabase  {

    abstract fun movieDao(): MovieDao
}