package ru.sulatskov.superapp.main.screens.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.databinding.FragmentGeneralBinding
import ru.sulatskov.superapp.main.ExtrasFragmentFactory
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.screens.contact.ContactFragment
import ru.sulatskov.superapp.main.screens.editText.EditTextFragment
import ru.sulatskov.superapp.main.screens.service_screen.ServiceFragment
import ru.sulatskov.superapp.main.screens.textView.TextViewFragment
import javax.inject.Inject

class GeneralFragment : BaseFragment(), GeneralContractInterface.View {

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
        router.openFragment(ExtrasFragmentFactory(ServiceFragment::class.java))
    }

    override fun openContentProviderScreen() {
        router.openFragment(ExtrasFragmentFactory(ContactFragment::class.java))
    }

    override fun openTextViewScreen() {
        router.openFragment(ExtrasFragmentFactory(TextViewFragment::class.java))
    }

    override fun openEditTextScreen() {
        router.openFragment(ExtrasFragmentFactory(EditTextFragment::class.java))
    }
}