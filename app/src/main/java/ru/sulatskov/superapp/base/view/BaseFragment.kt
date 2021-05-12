package ru.sulatskov.superapp.base.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.sulatskov.router.Router
import ru.sulatskov.router.RouterFactory
import ru.sulatskov.superapp.main.MainActivity
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var cntx: Context

    val router: Router by lazy { RouterFactory().create(cntx as AppCompatActivity) }

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
        MainActivity.component.inject(this)
        injectDependency()
        attachPresenter()
        initToolbar()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun injectDependency()
    abstract fun attachPresenter()
    abstract fun initToolbar()
}