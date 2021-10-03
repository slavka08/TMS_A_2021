package by.tms.homework25room.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import by.tms.homework25room.R
import by.tms.homework25room.databinding.ActivityMainBinding
import by.tms.homework25room.fragment.FilterFragment
import by.tms.homework25room.fragment.MainFragment
import by.tms.homework25room.fragment.SettingsFragment
import by.tms.homework25room.fragment.AddFragment


class MainActivity : AppCompatActivity(), MainActivityInterface {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setMainFragment()
    }

    private fun setMainFragment() {
        val fragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    private fun setAddFragment(editId: Int) {
        val fragment = when (editId > 0) {
            true -> AddFragment.newInstance(editId)
            false -> AddFragment.newInstance()
        }
        if (editId > 0) {
            AddFragment()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(AddFragment::class.java.toString())
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    private fun setFilterFragment() {
        val fragment = FilterFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(FilterFragment::class.java.toString())
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    private fun openSettings() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, SettingsFragment())
            .addToBackStack(AddFragment::class.java.toString())
            .commit()
    }

    override fun openAdd(editId: Int) {
        setAddFragment(editId)
    }

    override fun openMain() {
        setMainFragment()
    }

    override fun openFilter() {
        setFilterFragment()
    }

    override fun openSetting() {
        openSettings()
    }
}