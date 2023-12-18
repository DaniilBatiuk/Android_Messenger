package com.example.lab3.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.lab3.services.MsgService


class BootReceiver : BroadcastReceiver() {
    var mContext: Context? = null
    private val BOOT_ACTION = "android.intent.action.BOOT_COMPLETED"
    override fun onReceive(context: Context, intent: Intent) {
        mContext = context
        val action = intent.action
        if (action.equals(BOOT_ACTION, ignoreCase = true)) {

            val intent = Intent(context, MsgService::class.java)
            context.startService(intent)
            // в общем виде
            //для Activity
//            val activivtyIntent = Intent(context, MyActivity::class.java)
//            activivtyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(activivtyIntent)
//
//            //для Service
//            val serviceIntent = Intent(context, MyService::class.java)
//            context.startService(serviceIntent)
        }
    }
}