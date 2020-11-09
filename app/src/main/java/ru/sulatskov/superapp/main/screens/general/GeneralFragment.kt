package ru.sulatskov.superapp.main.screens.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.base.view.BaseViewInterface
import ru.sulatskov.superapp.databinding.FragmentGeneralBinding
import ru.sulatskov.superapp.di.component.DaggerFragmentComponent
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.screens.service_screen.ServicePresenter
import javax.inject.Inject

class GeneralFragment : BaseFragment(), BaseViewInterface {

    companion object {
        const val TAG = "GeneralFragment"
    }

    @Inject
    lateinit var presenter: GeneralPresenter

    private var fragmentBlankBinding: FragmentGeneralBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentGeneralBinding.inflate(inflater, container, false)
        fragmentBlankBinding = binding
        binding.description.text = getString(R.string.main_screen_text)
        binding.service.setOnClickListener {
            presenter.onServiceButtonClick()
        }
        return binding.root
    }

    override fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .build()

        aboutComponent.inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun onDestroyView() {
        fragmentBlankBinding = null
        super.onDestroyView()
    }

    fun openServiceScreen() {
        (activity as MainActivity).openServiceScreen()
    }
}