package ru.sulatskov.superapp.main.screens.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.databinding.FragmentGeneralBinding
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.router.Router
import javax.inject.Inject

class GeneralFragment : BaseFragment(), GeneralContractInterface.View {

    companion object {
        const val TAG = "GeneralFragment"
    }

    @Inject
    lateinit var presenter: GeneralPresenter

    @Inject
    lateinit var router: Router

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
        return binding.root
    }

    override fun injectDependency() {
        MainActivity.component.inject(this)
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
        router.openServiceScreen()
    }

    override fun openContentProviderScreen() {
        router.openContactScreen()
    }

    override fun openTextViewScreen() {
        router.openTextViewScreen()
    }

    override fun openEditTextScreen() {
        router.openEditTextScreen()
    }
}