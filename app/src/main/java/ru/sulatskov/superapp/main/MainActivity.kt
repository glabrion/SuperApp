package ru.sulatskov.superapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.di.component.MainComponent
import ru.sulatskov.superapp.di.module.ContextModule
import ru.sulatskov.superapp.main.router.Router
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var component: MainComponent
    }

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component =
            DaggerMainComponent.builder().contextModule(ContextModule(this))
                .build()
        component.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportFragmentManager.backStackEntryCount == 0) {
            router.openGeneralScreen()
        }
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