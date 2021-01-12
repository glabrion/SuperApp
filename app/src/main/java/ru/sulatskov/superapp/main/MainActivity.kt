package ru.sulatskov.superapp.main

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.databinding.ActivityMainBinding
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.editText.EditTextFragment
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.textView.TextViewFragment

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