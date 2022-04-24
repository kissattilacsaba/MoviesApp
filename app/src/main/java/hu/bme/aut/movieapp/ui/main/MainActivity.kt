package hu.bme.aut.movieapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.databinding.ActivityMainBinding
import hu.bme.aut.movieapp.ui.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var movieAdapter: MovieAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initRecyclerView()
        binding.apiButton.setOnClickListener {
            //initRecyclerView()
            moviesViewModel.testApi()
            apiButton.text = "Changed!"

        }
        binding.dbButton.setOnClickListener {
            moviesViewModel.getMovies()
            dbButton.text="Success!!"
        }

    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(this, moviesViewModel)
        listMovies.adapter = movieAdapter


        moviesViewModel.allMovies?.observe(this, {
                movies -> movieAdapter.submitList(movies)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}