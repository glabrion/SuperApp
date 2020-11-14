package ru.sulatskov.superapp.main.screens.service_screen

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.base.view.BaseFragment
import ru.sulatskov.superapp.base.view.BaseViewInterface
import ru.sulatskov.superapp.common.snackbar
import ru.sulatskov.superapp.databinding.FragmentServiceBinding
import ru.sulatskov.superapp.di.component.DaggerMainComponent
import ru.sulatskov.superapp.main.MainActivity
import ru.sulatskov.superapp.main.screens.service_screen.ServiceNotification.Companion.COUNT_NOTIFICATION
import ru.sulatskov.superapp.main.screens.service_screen.ServiceNotification.Companion.PARAM_PENDING_INTENT
import ru.sulatskov.superapp.main.screens.service_screen.ServiceNotification.Companion.STATUS_FINISH
import ru.sulatskov.superapp.main.screens.service_screen.ServiceNotification.Companion.STATUS_START
import ru.sulatskov.superapp.main.screens.service_screen.ServiceNotification.Companion.TASK_CODE
import javax.inject.Inject

class ServiceFragment : BaseFragment(), BaseViewInterface {

    companion object {
        const val TAG = "ServiceFragment"
        const val BROADCAST_ACTION = "BROADCAST_ACTION"
    }

    @Inject
    lateinit var presenter: ServicePresenter

    private var binding: FragmentServiceBinding? = null
    private var fragmentServiceBinding: FragmentServiceBinding? = null
    private var mainActivity: Activity? = null
    private val intentFilter = IntentFilter(BROADCAST_ACTION)
    private val toastReviver = ToastReviver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mainActivity = (activity as MainActivity)
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        fragmentServiceBinding = binding
        binding?.startService?.setOnClickListener {
            presenter.onStartServiceClick()
        }

        binding?.stopService?.setOnClickListener {
            presenter.onStopServiceClick()
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.registerReceiver(toastReviver, intentFilter)
    }

    override fun injectDependency() {
        DaggerMainComponent.builder().build().inject(this)
    }

    override fun attachPresenter() {
        presenter.attach(this)
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
        fragmentServiceBinding = null
        mainActivity?.unregisterReceiver(toastReviver)
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

    fun startService() {
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

    fun stopService() {
        showStopServiceToast()
        mainActivity?.stopService(Intent(view?.context, ServiceNotification::class.java))
    }

}