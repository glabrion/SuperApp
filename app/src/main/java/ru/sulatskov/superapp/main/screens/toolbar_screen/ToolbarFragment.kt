package ru.sulatskov.superapp.main.screens.toolbar_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.snackbar
import ru.sulatskov.superapp.databinding.FragmentAppBarBinding
import ru.sulatskov.superapp.databinding.FragmentToolbarBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.screens.app_bar_screen.AppBarContractInterface
import ru.sulatskov.superapp.main.screens.app_bar_screen.AppBarPresenter
import javax.inject.Inject

class ToolbarFragment : BaseFragment(), ToolbarContractInterface.View {

    companion object {
        const val TAG = "ToolbarFragment"
    }

    private var fragmentAppBarBinding: FragmentToolbarBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentToolbarBinding.inflate(inflater, container, false)
        fragmentAppBarBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun injectDependency() {
    }

    override fun initToolbar() {
        fragmentAppBarBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentAppBarBinding?.toolbar?.title = getString(R.string.collapsing_toolbar_layout_text)
    }


    override fun attachPresenter() {
    }
}