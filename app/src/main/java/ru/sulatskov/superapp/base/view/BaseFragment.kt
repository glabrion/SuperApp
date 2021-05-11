package ru.sulatskov.superapp.base.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.router.Router
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    fun hideKeyboard(activity: Activity?) {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectDependency()
        attachPresenter()
        initToolbar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun injectDependency()
    abstract fun attachPresenter()
    abstract fun initToolbar()
}