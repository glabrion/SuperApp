package ru.sulatskov.superapp.main.screens.general_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.databinding.FragmentGeneralBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.MainActivity
import javax.inject.Inject

class GeneralFragment : BaseFragment(), GeneralContractInterface.View {

    companion object {
        const val TAG = "GeneralFragmentTag"
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
        binding.textView.setOnClickListener {
            presenter.onTextViewButtonClick()
        }
        binding.editText.setOnClickListener {
            presenter.onEditTextButtonClick()
        }
        binding.buttonScreen.setOnClickListener {
            presenter.onButtonScreenClick()
        }
        binding.appBar.setOnClickListener {
            presenter.onAppBarButtonClick()
        }
        binding.toolbarButton.setOnClickListener {
            presenter.onToolbarButtonClick()
        }
        binding.imageViewButton.setOnClickListener {
            presenter.onImageViewButtonClick()
        }
        binding.workManager.setOnClickListener {
            presenter.onWorkManagerButtonClick()
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

    override fun openServiceScreen() {
        (activity as? MainActivity)?.openServiceScreen()
    }

    override fun openContentProviderScreen() {
        (activity as? MainActivity)?.openContactScreen()
    }

    override fun openTextViewScreen() {
        (activity as? MainActivity)?.openTextViewScreen()
    }

    override fun openEditTextScreen() {
        (activity as? MainActivity)?.openEditTextScreen()
    }

    override fun openButtonScreen() {
        (activity as? MainActivity)?.openButtonScreen()
    }

    override fun openAppBarScreen() {
        (activity as? MainActivity)?.openAppBarScreen()
    }

    override fun openToolbarScreen() {
        (activity as? MainActivity)?.openToolbarScreen()
    }

    override fun openImageViewScreen() {
        (activity as? MainActivity)?.openImageViewScreen()
    }

    override fun openWorkManagerScreen() {
        (activity as? MainActivity)?.openWorkManagerScreen()
    }
}