package ru.sulatskov.superapp.main.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.databinding.FragmentGeneralBinding
import ru.sulatskov.superapp.main.MainActivity

class GeneralFragment : Fragment() {
    companion object {
        const val TAG = "GeneralFragment"
    }

    private var fragmentBlankBinding: FragmentGeneralBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGeneralBinding.inflate(inflater, container, false)
        fragmentBlankBinding = binding
        binding.description.text = getString(R.string.main_screen_text)
        binding.service.setOnClickListener {
            (activity as MainActivity).openServiceScreen()
        }
        return binding.root
    }

    override fun onDestroyView() {
        fragmentBlankBinding = null
        super.onDestroyView()
    }
}