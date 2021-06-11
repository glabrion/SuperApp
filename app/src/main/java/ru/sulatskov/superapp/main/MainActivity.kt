package ru.sulatskov.superapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.main.screens.app_bar_screen.AppBarFragment
import ru.sulatskov.superapp.main.screens.button_screen.ButtonFragment
import ru.sulatskov.superapp.main.screens.content_provider_screen.ContactFragment
import ru.sulatskov.superapp.main.screens.edit_text_screen.EditTextFragment
import ru.sulatskov.superapp.main.screens.general_screen.GeneralFragment
import ru.sulatskov.superapp.main.screens.image_view_screen.ImageViewFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.text_view_screen.TextViewFragment
import ru.sulatskov.superapp.main.screens.toolbar_screen.ToolbarFragment
import ru.sulatskov.superapp.main.screens.work_manager_screen.WorkManagerFragment

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
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<GeneralFragment>(R.id.fragment_container_view, GeneralFragment.TAG)
            addToBackStack(GeneralFragment.TAG)
        }
    }

    fun openServiceScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ServiceFragment>(R.id.fragment_container_view, ServiceFragment.TAG)
            addToBackStack(ServiceFragment.TAG)
        }
    }

    fun openContactScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ContactFragment>(R.id.fragment_container_view, ContactFragment.TAG)
            addToBackStack(ContactFragment.TAG)
        }
    }

    fun openTextViewScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TextViewFragment>(R.id.fragment_container_view, TextViewFragment.TAG)
            addToBackStack(TextViewFragment.TAG)
        }
    }

    fun openEditTextScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<EditTextFragment>(R.id.fragment_container_view, EditTextFragment.TAG)
            addToBackStack(EditTextFragment.TAG)
        }
    }

    fun openButtonScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ButtonFragment>(R.id.fragment_container_view, ButtonFragment.TAG)
            addToBackStack(ButtonFragment.TAG)
        }
    }

    fun openAppBarScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<AppBarFragment>(R.id.fragment_container_view, AppBarFragment.TAG)
            addToBackStack(AppBarFragment.TAG)
        }
    }

    fun openToolbarScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ToolbarFragment>(R.id.fragment_container_view, ToolbarFragment.TAG)
            addToBackStack(ToolbarFragment.TAG)
        }
    }

    fun openImageViewScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ImageViewFragment>(R.id.fragment_container_view, ImageViewFragment.TAG)
            addToBackStack(ImageViewFragment.TAG)
        }
    }

   fun openWorkManagerScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<WorkManagerFragment>(R.id.fragment_container_view, WorkManagerFragment.TAG)
            addToBackStack(WorkManagerFragment.TAG)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val serviceFragment = supportFragmentManager.findFragmentByTag(ServiceFragment.TAG)
        if (serviceFragment != null && serviceFragment.isVisible) {
            serviceFragment.onActivityResult(requestCode, resultCode, data)
        }
        val imageViewFragment = supportFragmentManager.findFragmentByTag(ImageViewFragment.TAG)
        if (imageViewFragment != null && imageViewFragment.isVisible) {
            imageViewFragment.onActivityResult(requestCode, resultCode, data)
        }


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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}