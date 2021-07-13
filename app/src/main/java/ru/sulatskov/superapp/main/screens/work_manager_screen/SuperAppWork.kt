package ru.sulatskov.superapp.main.screens.work_manager_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.*
import ru.sulatskov.superapp.main.MainActivity
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class SuperAppWork(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun doWork(): Result {
        launch {
            Toast.makeText(context, "Worker: start", Toast.LENGTH_SHORT).show()
            delay(2000)
            Toast.makeText(context, "Worker: in process", Toast.LENGTH_SHORT).show()
            delay(2000)
            Toast.makeText(context, "Worker: end", Toast.LENGTH_SHORT).show()
        }
        return Result.success()
    }
}