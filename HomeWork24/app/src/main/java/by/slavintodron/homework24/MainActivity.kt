package by.slavintodron.homework24

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.slavintodron.homework24.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),FragmentListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        openListFragment()
        setContentView(binding.root)
    }


    private fun openItemFragment(id:Int) {
        val fragment = ItemFragment.newInstance(id)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment)
            addToBackStack(null)
        }
    }

    private fun openListFragment() {
        val fragment = ItemsFragment()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainerView, fragment)

        }
    }

    override fun openRvFragment() {
        openListFragment()
    }

    override fun openItem(id: Int) {
        openItemFragment(id)
    }
}