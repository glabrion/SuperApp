package ru.sulatskov.superapp.main.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.snackbar
import ru.sulatskov.superapp.databinding.FragmentServiceBinding

class ServiceFragment : BaseFragment() {

    private var fragmentBlankBinding: FragmentServiceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentServiceBinding.inflate(inflater, container, false)
        fragmentBlankBinding = binding
        binding.startService.setOnClickListener {
            view?.context?.getString(R.string.start_service)?.let { message -> snackbar(message) }
        }

        binding.stopService.setOnClickListener {
            view?.context?.getString(R.string.stop_service)?.let { message -> snackbar(message) }
        }
        return binding.root
    }

    override fun onDestroyView() {
        fragmentBlankBinding = null
        super.onDestroyView()
    }
}