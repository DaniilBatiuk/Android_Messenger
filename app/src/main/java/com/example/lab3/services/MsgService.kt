package com.example.lab3.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MsgService : Service() {
    private val TAG = "MsgService"

    val BROADCAST_ACTION = "com.itstep.messanger.servicebackbroadcastmessage"
    val PARAM_TASK          = "task"
    val PARAM_USERS_LIST    = "users_list"
    val PARAM_TEXT_MSG      = "textMsg"
    val PARAM_USER_NICK      = "userNick"

    val TASK_MSH = 1
    val TASK_USERS = 2

    var bWorkService: Boolean = true

    override fun onCreate() {
        Log.i(TAG, "Service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i(TAG, "Service onStartCommand " + startId)

        var time = intent?.getIntExtra("time",0)

//        var bWorkService: Boolean = true
//        var count = 0;
//        while (bWorkService) {
//
//            try {
//                Thread.sleep(2000)
//                Log.i(TAG, "Check user Messages")
//
//                count++;
//
//                if (count > 5)
//                    bWorkService = false;
//
//            } catch (e: Exception) {
//            }
//            Log.i(TAG, "Service running $count")
//        }
//
//        Log.i(TAG, "Service finish")
        someTask()

        return Service.START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.i(TAG, "Service onBind")
        return null
    }

    fun someTask() {
        Thread {
            var i:Int = 0;

            while (bWorkService) {
                val intent = Intent(BROADCAST_ACTION)
                try {
                    Thread.sleep(1000)
                    Log.i(TAG, "Check user Messages")

                    if (i == 3) {
                        Log.i(TAG, "Check users!!! - $i")

                        // сообщаем о старте задачи
                        intent.putExtra(PARAM_TASK, TASK_USERS)
                        intent.putExtra(PARAM_USERS_LIST, "Vasya - online, Olya - offlline, Kolya - online")
                        sendBroadcast(intent)
                    }

                    if (i == 5) {
                        Log.i(TAG, "Check message!!! - $i")
                        intent.putExtra(PARAM_TASK, TASK_MSH)
                        intent.putExtra(PARAM_TEXT_MSG, "Hello Kity ;-)))")
                        intent.putExtra(PARAM_USER_NICK, "Vasya")

                        sendBroadcast(intent)
                    }

                    if (i == 6) {
                        Log.i(TAG, "Check users!!! - $i")
                        intent.putExtra(PARAM_TASK, TASK_USERS)
                        intent.putExtra(PARAM_USERS_LIST, "Vasya - online, Olya - online, Kolya - offlline")
                        sendBroadcast(intent)
                    }

                    if (i == 9) {
                        Log.i(TAG, "Check users!!! - $i")

                        // сообщаем о старте задачи
                        intent.putExtra(PARAM_TASK, TASK_USERS)
                        intent.putExtra(PARAM_USERS_LIST, "Vasya - online, Olya - offlline, Kolya - offlline")
                        sendBroadcast(intent)
                    }

                    if (i == 10) {
                        Log.i(TAG, "Check message!!! - $i")
                        intent.putExtra(PARAM_TASK, TASK_MSH)
                        intent.putExtra(PARAM_TEXT_MSG, "By-By")
                        intent.putExtra(PARAM_USER_NICK, "Vasya")

                        sendBroadcast(intent)
                        i = 0;
                    }

                    Log.i(TAG, "Service working!!! - $i")
                    i++;

                } catch (e: Exception) {
                }
                Log.i(TAG, "Service running")
            }
            stopSelf()
        }.start()
    }

    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
        bWorkService = false
    }
}