package ru.sulatskov.superapp.main.screens.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.base.view.BaseViewInterface
import ru.sulatskov.superapp.databinding.FragmentGeneralBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.MainActivity
import javax.inject.Inject

class GeneralFragment : BaseFragment(), BaseViewInterface {

    companion object {
        const val TAG = "GeneralFragment"
    }

    @Inject
    lateinit var presenter: GeneralPresenter

    private var fragmentGeneralBinding: FragmentGeneralBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentGeneralBinding.inflate(inflater, container, false)
        fragmentGeneralBinding = binding
        binding.service.setOnClickListener {
            presenter.onServiceButtonClick()
        }
        binding.contentProvider.setOnClickListener {
            presenter.onContentProviderButtonClick()
        }
        return binding.root
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun initToolbar() {
    }

    override fun onDestroyView() {
        fragmentGeneralBinding = null
        super.onDestroyView()
    }

    fun openServiceScreen() {
        (activity as? MainActivity)?.openServiceScreen()
    }

    fun openContentProviderScreen() {
        (activity as? MainActivity)?.openContactScreen()
    }
}