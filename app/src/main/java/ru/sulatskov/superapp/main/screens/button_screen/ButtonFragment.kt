package ru.sulatskov.superapp.main.screens.button_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.ProgressButton
import ru.sulatskov.superapp.common.toast
import ru.sulatskov.superapp.databinding.FragmentButtonBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import javax.inject.Inject

class ButtonFragment : BaseFragment(), ButtonsContractInterface.View {

    companion object {
        const val TAG = "ButtonFragment"
    }

    @Inject
    lateinit var presenter: ButtonPresenter

    private var fragmentButtonBinding: FragmentButtonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentButtonBinding.inflate(inflater, container, false)
        fragmentButtonBinding = binding
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentButtonBinding?.imageButton?.setOnClickListener {
            presenter.onImageButtonClick()
        }
        fragmentButtonBinding?.buttonDrawable?.setOnClickListener {
            presenter.onButtonDrawableClick()
        }
        fragmentButtonBinding?.progressButton?.setOnClickListener {
            presenter.onProgressButtonClick()
        }
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    override fun initToolbar() {
        fragmentButtonBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        fragmentButtonBinding?.toolbar?.title = getString(R.string.buttons_text)

    }

    override fun onDestroyView() {
        fragmentButtonBinding = null
        super.onDestroyView()
    }

    override fun showToast(type: ButtonPresenter.ToastType) {
        when (type) {
            ButtonPresenter.ToastType.IMAGE_BUTTON -> toast(getString(R.string.image_button))
            ButtonPresenter.ToastType.IMAGE_DRAWABLE -> toast(getString(R.string.image_drawable))
            ButtonPresenter.ToastType.PROGRESS_COMPLETE -> toast(getString(R.string.progress_complete))
        }

    }

    override fun startProgress() {
        fragmentButtonBinding?.progressButton?.setState(state = ProgressButton.State.IN_PROGRESS)
    }

    override fun stopProgress() {
        fragmentButtonBinding?.progressButton?.setState(state = ProgressButton.State.COMPLETE)
    }
}