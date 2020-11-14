package ru.sulatskov.hiltexample.main.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ru.sulatskov.hiltexample.R
import ru.sulatskov.hiltexample.databinding.ActivityMainBinding
import ru.sulatskov.hiltexample.main.screens.general.GeneralFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportFragmentManager.backStackEntryCount == 0) {
            openGeneralScreen()
        }
    }

    private fun openGeneralScreen() {
        val generalFragment = GeneralFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.main_fragment_container,
            generalFragment, GeneralFragment.TAG
        ).addToBackStack(GeneralFragment.TAG)
            .commit()
    }
}