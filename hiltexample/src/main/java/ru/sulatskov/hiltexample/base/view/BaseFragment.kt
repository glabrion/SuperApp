package ru.sulatskov.hiltexample.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        attachPresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun attachPresenter()
}