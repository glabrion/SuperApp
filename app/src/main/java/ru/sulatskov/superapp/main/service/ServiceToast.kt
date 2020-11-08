package ru.sulatskov.superapp.main.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import ru.sulatskov.superapp.main.MainActivity

class ServiceToast: Service() {

    var pendingIntent: PendingIntent? = null
    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand")
        val countPush = intent?.getIntExtra(COUNT_PUSH_NOTIFICATION, 0)
        pendingIntent = intent?.getParcelableExtra(PARAM_PENDING_INTENT)
        countPush?.let {
            work(it)
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "onBind")
        return null
    }

    private fun work(countPush: Int) {
        for (item in 0..countPush){
            Thread.sleep(500)
            Log.d(LOG_TAG, item.toString())
        }
        pendingIntent?.send(this, STATUS_FINISH, Intent())
    }

    companion object {
        const val LOG_TAG = "ServiceToast"
        const val COUNT_PUSH_NOTIFICATION = "COUNT_PUSH_NOTIFICATION"
        const val PARAM_PENDING_INTENT = "PARAM_PENDING_INTENT"
        const val STATUS_FINISH = 100
        const val TASK_CODE = 1

    }

}