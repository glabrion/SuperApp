package ru.sulatskov.superapp.main.service

import android.app.Activity
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.common.snackbar
import ru.sulatskov.superapp.common.toastInCenter
import ru.sulatskov.superapp.databinding.FragmentServiceBinding
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.COUNT_NOTIFICATION
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.MESSAGE_NOTIFICATION
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.PARAM_PENDING_INTENT
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.PARAM_STATUS
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.STATUS_FINISH
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.STATUS_IN_PROGRESS
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.STATUS_START
import ru.sulatskov.superapp.main.service.ServiceNotification.Companion.TASK_CODE

class ServiceFragment : BaseFragment() {

    companion object {
        const val TAG = "ServiceFragment"
        const val BROADCAST_ACTION = "BROADCAST_ACTION"
    }

    private var binding: FragmentServiceBinding? = null
    private var fragmentBlankBinding: FragmentServiceBinding? = null
    private var mainActivity: Activity? = null
    private val intentFilter = IntentFilter(BROADCAST_ACTION)
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val status = intent?.getIntExtra(PARAM_STATUS, 0)
            val message = intent?.getStringExtra(MESSAGE_NOTIFICATION)

            if (status == STATUS_IN_PROGRESS) {
                message?.let {
                    toastInCenter(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = (activity as MainActivity)
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        fragmentBlankBinding = binding
        binding?.startService?.setOnClickListener {
            try {
                val countNotification: Int = binding?.notificationCount?.text.toString().toInt()
                val pendingIntent: PendingIntent? =
                    mainActivity?.createPendingResult(TASK_CODE, Intent(), 0)
                if (countNotification > 0) {
                    mainActivity?.startService(
                        Intent(view?.context, ServiceNotification::class.java).putExtra(
                            COUNT_NOTIFICATION,
                            countNotification
                        ).putExtra(PARAM_PENDING_INTENT, pendingIntent)
                    )
                }
                hideKeyboard(mainActivity)
            } catch (e: Exception) {
                binding?.notificationCount?.error = view?.context?.getString(R.string.insert_number)
                showErrorToast()
            }
        }

        binding?.stopService?.setOnClickListener {
            showStopServiceToast()
            mainActivity?.stopService(Intent(view?.context, ServiceNotification::class.java))
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {

            STATUS_START -> {
                showStartServiceToast()
            }

            STATUS_FINISH -> {
                showServiceFinishedToast()
            }
        }
    }

    override fun onDestroyView() {
        fragmentBlankBinding = null
        mainActivity?.unregisterReceiver(broadcastReceiver)
        super.onDestroyView()
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
}