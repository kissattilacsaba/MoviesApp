package hu.bme.aut.movieapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.wrappers.InstantApps
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var touchHelper: ItemTouchHelper
    lateinit var movieAdapter: MovieAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initRecyclerView()
        binding.apiButton.setOnClickListener {
            moviesViewModel.search(binding.editTextSearch.text.toString())
            touchHelper.attachToRecyclerView(null)
        }

        touchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder): Boolean { return false }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                movieAdapter.deleteItem(viewHolder.adapterPosition)
            }
        })

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "MainActivity")
        firebaseAnalytics.logEvent("MainActivity", bundle)

    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter(this, moviesViewModel)
        listMovies.adapter = movieAdapter


        moviesViewModel.allMovies.observe(this) {
                movies -> movieAdapter.submitList(movies)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_saved_movies -> {
                moviesViewModel.getMovies()
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Search")
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle)
                touchHelper.attachToRecyclerView(binding.listMovies)
                true
            }
            R.id.action_delete_movies -> {
                moviesViewModel.deleteAll()
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Delete all")
                firebaseAnalytics.logEvent("Delete_all", bundle)
                touchHelper.attachToRecyclerView(binding.listMovies)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}