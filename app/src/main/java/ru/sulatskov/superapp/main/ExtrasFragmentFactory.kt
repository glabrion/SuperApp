package ru.sulatskov.superapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.sulatskov.router.FragmentFactory
import ru.sulatskov.superapp.R


class ExtrasFragmentFactory(private val fragment: Class<out Fragment>,
                            private val extras: Bundle? = null
): FragmentFactory() {

    override fun create(): Fragment {
        val instance = fragment.newInstance()
        instance.arguments = createArguments()
        return instance
    }

    override fun createArguments(): Bundle? = extras

    override fun getContainer(): Int = R.id.fragment_container_view

    override fun getBackStackTag(): String? = fragment.name
}