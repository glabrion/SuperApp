package ru.sulatskov.superapp.main.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import ru.sulatskov.superapp.R
import ru.sulatskov.superapp.main.service.ServiceFragment.Companion.BROADCAST_ACTION
import java.text.DateFormat
import java.util.*


class ServiceNotification : Service() {

    private var pendingIntent: PendingIntent? = null
    private var notificationThread = NotificationThread()
    private var isStopped: Boolean = true

    override fun onCreate() {
        super.onCreate()
        notificationThread.start()
        Log.d(LOG_TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand")
        val countNotification = intent?.getIntExtra(COUNT_NOTIFICATION, 0)
        pendingIntent = intent?.getParcelableExtra(PARAM_PENDING_INTENT)
        countNotification?.let {
            execute(it)
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        notificationThread.stopThread()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "onBind")
        return null
    }

    private fun execute(countNotification: Int) {
        for (item in 0 until countNotification) {
            Thread.sleep(1000)
            val intent = Intent(BROADCAST_ACTION)
            val currentDateTimeString =
                DateFormat.getDateTimeInstance().format(Date())
            intent.putExtra(MESSAGE_NOTIFICATION, currentDateTimeString)
            intent.putExtra(PARAM_STATUS, STATUS_IN_PROGRESS)
            sendBroadcast(intent)
            Log.d(LOG_TAG, item.toString())
        }

        pendingIntent?.send(this, STATUS_FINISH, Intent())
    }

    private fun sendNotification() {

        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification =
            NotificationCompat.Builder(baseContext, "notification_id")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.service_work))
                .setContentText(getString(R.string.serviceNotification_still_works))
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .build()
        notificationManager.notify(0, notification)
    }

    companion object {
        const val LOG_TAG = "ServiceToast"
        const val COUNT_NOTIFICATION = "COUNT_NOTIFICATION"
        const val PARAM_PENDING_INTENT = "PARAM_PENDING_INTENT"
        const val PARAM_STATUS = "PARAM_STATUS"
        const val STATUS_START = 100
        const val STATUS_IN_PROGRESS = 200
        const val STATUS_FINISH = 300
        const val MESSAGE_NOTIFICATION = "MESSAGE_NOTIFICATION"
        const val TASK_CODE = 1

    }

    inner class NotificationThread: Thread() {
        override fun run() {
            super.run()
            isStopped = false
            while (!isStopped){
                sleep(5000)
                sendNotification()
                Log.d(LOG_TAG, "sendNotification")
            }
        }

        fun stopThread(){
            isStopped = true
        }
    }

}

