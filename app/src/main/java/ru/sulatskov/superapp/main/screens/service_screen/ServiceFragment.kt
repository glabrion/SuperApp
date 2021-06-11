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

class ServiceFragment : BaseFragment(), ServiceContractInterface.View {

    companion object {
        const val TAG = "ServiceFragmentTag"
        const val BROADCAST_ACTION = "BROADCAST_ACTION"
    }

    @Inject
    lateinit var presenter: ServicePresenter

    private var fragmentServiceBinding: FragmentServiceBinding? = null
    private var mainActivity: Activity? = null
    private val intentFilter = IntentFilter(BROADCAST_ACTION)
    private val toastReviver = ToastReceiver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = (activity as MainActivity)
        fragmentServiceBinding = FragmentServiceBinding.inflate(inflater, container, false)
        fragmentServiceBinding?.startService?.setOnClickListener {
            presenter.onStartServiceClick()
        }

        fragmentServiceBinding?.stopService?.setOnClickListener {
            presenter.onStopServiceClick()
        }
        super.onCreateView(inflater, container, savedInstanceState)
        return fragmentServiceBinding?.root
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

    override fun initToolbar() {
        fragmentServiceBinding?.toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        fragmentServiceBinding?.toolbar?.title =  getString(R.string.service)
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

    override fun showStartServiceToast() {
        view?.context?.getString(R.string.start_service)
            ?.let { message -> snackbar(message) }
    }

    override fun showErrorToast() {
        view?.context?.getString(R.string.insert_number)
            ?.let { message -> snackbar(message) }
    }

    override fun showStopServiceToast() {
        view?.context?.getString(R.string.stop_service)?.let { message -> snackbar(message) }
    }

    override fun showServiceFinishedToast() {
        view?.context?.getString(R.string.service_finished)?.let { message -> snackbar(message) }
    }

    override fun startService() {
        try {
            val countNotification: Int = fragmentServiceBinding?.notificationCount?.text.toString().toInt()
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
            fragmentServiceBinding?.notificationCount?.error = view?.context?.getString(R.string.insert_number)
            showErrorToast()
        }
    }

    override fun stopService() {
        showStopServiceToast()
        mainActivity?.stopService(Intent(view?.context, ServiceNotification::class.java))
    }

}