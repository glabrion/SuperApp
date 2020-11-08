package ru.sulatskov.superapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.main.general.GeneralFragment
import ru.sulatskov.superapp.main.service.ServiceFragment

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

    fun openServiceScreen() {
        val serviceFragment = ServiceFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.main_fragment_container,
            serviceFragment, ServiceFragment.TAG
        ).addToBackStack(ServiceFragment.TAG)
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentByTag(ServiceFragment.TAG)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }
}