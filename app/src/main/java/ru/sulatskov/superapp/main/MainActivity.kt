package ru.sulatskov.superapp.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.main.general.GeneralFragment

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
      generalFragment
    ).addToBackStack(generalFragment.tag)
      .commit()
  }
}