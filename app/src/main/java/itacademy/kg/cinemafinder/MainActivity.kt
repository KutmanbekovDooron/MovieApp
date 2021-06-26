package itacademy.kg.cinemafinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import itacademy.kg.cinemafinder.databinding.ActivityMainBinding
import itacademy.kg.cinemafinder.ui.HomeFragment
import itacademy.kg.cinemafinder.ui.RatingFragment

class MainActivity : AppCompatActivity() {
    lateinit var _binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home -> setFragment(HomeFragment())
                R.id.favorites ->Toast.makeText(this,"Favorites",Toast.LENGTH_SHORT).show()
                R.id.ratings -> setFragment(RatingFragment())
            }
            true
        }

        setFragment(HomeFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setFragment  (fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }
    }
}