package hu.bme.aut.movieapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import hu.bme.aut.movieapp.perstistence.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


class DatabaseTest {
    private lateinit var database: AppDatabase

    @Before
    fun initDB() {
        database = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB() {
        database.close()
    }


}