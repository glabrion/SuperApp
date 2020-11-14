package ru.sulatskov.superapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment

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

    fun openContactScreen() {
        val contactFragment = ContactFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.main_fragment_container,
            contactFragment, ContactFragment.TAG
        ).addToBackStack(ContactFragment.TAG)
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentByTag(ServiceFragment.TAG)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment = supportFragmentManager.findFragmentByTag(ContactFragment.TAG)
        fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}