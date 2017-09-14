package com.dynamicheart.raven.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.avos.avoscloud.AVOSCloud
import com.dynamicheart.raven.R
import com.dynamicheart.raven.ui.inraven.InRavenActivity
import org.json.JSONObject
import timber.log.Timber

class RavenReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            if (intent.action == "com.dynamicheart.raven.push") {
                val json = JSONObject(intent.extras.getString("com.avos.avoscloud.Data"))

                Timber.i("installation %s", json.toString())

                val alert = json.getString("alert")
                val title = json.getString("title")
                val ravenId = json.getString("ravenId")

                val resultIntent = Intent(AVOSCloud.applicationContext, InRavenActivity::class.java)
                resultIntent.putExtra(InRavenActivity.EXTRA_RAVEN_ID, ravenId)
                val pendingIntent = PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT)
                val builder = NotificationCompat.Builder(AVOSCloud.applicationContext)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(alert)
                        .setContentText(title)
                        .setTicker(title)
                builder.setContentIntent(pendingIntent)
                builder.setAutoCancel(true)

                val notificationId = 10086
                val notificationManager = AVOSCloud.applicationContext
                        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(notificationId, builder.build())
            }
        } catch (e: Exception) {
            Timber.i("Could not notify")
        }
    }
}