package ru.sulatskov.superapp.main.screens.edit_text_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.databinding.FragmentEditTextBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import javax.inject.Inject

class EditTextFragment : BaseFragment(), EditTextContractInterface.View {

    companion object {
        const val TAG = "EditTextFragmentTag"
    }

    @Inject
    lateinit var presenter: EditTextPresenter

    private var fragmentEditTextBinding: FragmentEditTextBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditTextBinding.inflate(inflater, container, false)
        fragmentEditTextBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentEditTextBinding?.loginBtn?.isEnabled = false
        fragmentEditTextBinding?.loginBtn?.setOnClickListener {
            presenter.onLoginButtonClick()
        }

        fragmentEditTextBinding?.loginEt?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fragmentEditTextBinding?.loginBtn?.isEnabled =
                    !s.isNullOrEmpty() && fragmentEditTextBinding?.passwordEt?.text?.isNotEmpty() == true
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        fragmentEditTextBinding?.passwordEt?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fragmentEditTextBinding?.loginBtn?.isEnabled =
                    !s.isNullOrEmpty() && fragmentEditTextBinding?.loginEt?.text?.isNotEmpty() == true
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        fragmentEditTextBinding?.inputEt?.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                fragmentEditTextBinding?.focusTv?.text = getString(R.string.has_focus)
            } else {
                fragmentEditTextBinding?.focusTv?.text = getString(R.string.has_not_focus)
            }
        }

        fragmentEditTextBinding?.inputEt?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fragmentEditTextBinding?.etResult?.text = fragmentEditTextBinding?.inputEt?.text.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun initToolbar() {
        fragmentEditTextBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentEditTextBinding?.toolbar?.title = getString(R.string.edit_text)

    }

    override fun onDestroyView() {
        fragmentEditTextBinding = null
        super.onDestroyView()
    }

    override fun showLoginAndPassword() {
        hideKeyboard(activity)
        fragmentEditTextBinding?.loginText?.text =
            fragmentEditTextBinding?.loginEt?.text.toString()
        fragmentEditTextBinding?.passwordText?.text =
            fragmentEditTextBinding?.passwordEt?.text.toString()
    }
}