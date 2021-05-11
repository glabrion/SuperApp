package ru.sulatskov.superapp.main.router

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.editText.EditTextFragment
import ru.sulatskov.superapp.main.screens.general.GeneralFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.textView.TextViewFragment
import javax.inject.Inject

class Router {

    @Inject
    lateinit var context: Context

    init {
        MainActivity.component.inject(this)
    }

    fun openGeneralScreen() {
        (context as FragmentActivity).supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<GeneralFragment>(R.id.fragment_container_view, GeneralFragment.TAG)
            addToBackStack(GeneralFragment.TAG)
        }
    }

    fun openServiceScreen() {
        (context as FragmentActivity).supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ServiceFragment>(R.id.fragment_container_view, ServiceFragment.TAG)
            addToBackStack(ServiceFragment.TAG)
        }
    }

    fun openContactScreen() {
        (context as FragmentActivity).supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ContactFragment>(R.id.fragment_container_view, ContactFragment.TAG)
            addToBackStack(ContactFragment.TAG)
        }
    }

    fun openTextViewScreen() {
        (context as FragmentActivity).supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<TextViewFragment>(R.id.fragment_container_view, TextViewFragment.TAG)
            addToBackStack(TextViewFragment.TAG)
        }
    }

    fun openEditTextScreen() {
        (context as FragmentActivity).supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<EditTextFragment>(R.id.fragment_container_view, EditTextFragment.TAG)
            addToBackStack(EditTextFragment.TAG)
        }

    }

}