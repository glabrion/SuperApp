package ru.sulatskov.router

import androidx.appcompat.app.AppCompatActivity

internal class PhoneRouter(activity: AppCompatActivity) : Router(activity) {

    override fun getContainerFor(fragmentFactory: FragmentFactory): Int? = fragmentFactory.getContainer()

    override fun isLastFragment(): Boolean {
        return activity.get()?.let { it.supportFragmentManager.backStackEntryCount <= 1 } ?: false
    }
}