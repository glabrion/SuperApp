package ru.sulatskov.hiltexample.main.screens.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import ru.sulatskov.hiltexample.R
import ru.sulatskov.hiltexample.base.view.BaseFragment
import ru.sulatskov.hiltexample.base.view.BaseViewInterface
import ru.sulatskov.hiltexample.databinding.FragmentGeneralBinding
import javax.inject.Inject

@AndroidEntryPoint
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentGeneralBinding?.getToast?.setOnClickListener {
            presenter.onGetToastClick()
        }
    }

    override fun attachPresenter() {
        presenter.attach(this)
    }

    fun showToast() {
        Toast.makeText(context, getString(R.string.hilt_toast), Toast.LENGTH_SHORT).show()
    }

}