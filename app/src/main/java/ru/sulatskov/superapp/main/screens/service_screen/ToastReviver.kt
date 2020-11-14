package ru.sulatskov.superapp.main.screens.service_screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast

class ToastReviver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val status = intent?.getIntExtra(ServiceNotification.PARAM_STATUS, 0)
        val message = intent?.getStringExtra(ServiceNotification.MESSAGE_NOTIFICATION)

        if (status == ServiceNotification.STATUS_IN_PROGRESS) {
            message?.let {
                    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
            }
        }
    }
}