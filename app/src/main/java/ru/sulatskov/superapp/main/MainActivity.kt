package ru.sulatskov.superapp.main

import android.content.Intent
import android.os.Bundle
import ru.sulatskov.router.Router
import ru.sulatskov.router.RouterFactory
import ru.sulatskov.superapp.base.view.BaseActivity
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.di.component.MainComponent
import ru.sulatskov.superapp.di.module.ContextModule
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment

class MainActivity : BaseActivity() {

    companion object {
        lateinit var component: MainComponent
    }

    private lateinit var binding: ActivityMainBinding

    val router: Router by lazy { RouterFactory().create(this) }

    override fun injectDependency() {
        component =
            DaggerMainComponent.builder().contextModule(ContextModule(this))
                .build()
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportFragmentManager.backStackEntryCount == 0) {
            router.openFragment(ExtrasFragmentFactory(GeneralFragment::class.java))
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