package ru.sulatskov.superapp.main.service

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.hideKeyboard
import ru.sulatskov.superapp.common.snackbar
import ru.sulatskov.superapp.databinding.FragmentServiceBinding
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.service.ServiceToast.Companion.COUNT_PUSH_NOTIFICATION
import ru.sulatskov.superapp.main.service.ServiceToast.Companion.PARAM_PENDING_INTENT
import ru.sulatskov.superapp.main.service.ServiceToast.Companion.STATUS_FINISH
import ru.sulatskov.superapp.main.service.ServiceToast.Companion.TASK_CODE
import java.lang.Exception

class ServiceFragment : BaseFragment() {

    companion object{
        const val TAG = "ServiceFragment"
    }

    private var fragmentBlankBinding: FragmentServiceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = (activity as MainActivity)
        val binding = FragmentServiceBinding.inflate(inflater, container, false)
        fragmentBlankBinding = binding
        binding.stopService.isEnabled = false
        binding.startService.setOnClickListener {
            try {
                val countPushNotification: Int = binding.pushCount.text.toString().toInt()
                val pendingIntent: PendingIntent =
                    mainActivity.createPendingResult(TASK_CODE, Intent(), 0)
                binding.stopService.isEnabled = true
                it.isEnabled = false
                if (countPushNotification > 0) {
                    showStartServiceToast()
                    mainActivity.startService(
                        Intent(view?.context, ServiceToast::class.java).putExtra(
                            COUNT_PUSH_NOTIFICATION,
                            countPushNotification
                        ).putExtra(PARAM_PENDING_INTENT, pendingIntent)
                    )
                }
                hideKeyboard(mainActivity)
            } catch (e: Exception) {
                binding.pushCount.error = view?.context?.getString(R.string.insert_number)
                showErrorToast()
            }
        }

        binding.stopService.setOnClickListener {
            showStopServiceToast()
            mainActivity.stopService(Intent(view?.context, ServiceToast::class.java))
            binding.stopService.isEnabled = false
            binding.startService.isEnabled = true
        }
        return binding.root
    }

    private fun showStartServiceToast() {
        view?.context?.getString(R.string.start_service)
            ?.let { message -> snackbar(message) }
    }

    private fun showErrorToast() {
        view?.context?.getString(R.string.insert_number)
            ?.let { message -> snackbar(message) }
    }

    private fun showStopServiceToast() {
        view?.context?.getString(R.string.stop_service)?.let { message -> snackbar(message) }
    }

    private fun showServiceFinishedToast() {
        view?.context?.getString(R.string.service_finished)?.let { message -> snackbar(message) }
    }

    override fun onDestroyView() {
        fragmentBlankBinding = null
        super.onDestroyView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == STATUS_FINISH) {
            showServiceFinishedToast()
        }
    }
}