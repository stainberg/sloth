package com.stainberg.sloth.toast

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.stainberg.sloth.R
import com.stainberg.sloth.ui.activity.BlankActivity


class ToastService {

    private lateinit var context: Context
    private lateinit var notification: Notification
    private lateinit var notificationManager: NotificationManager

    companion object {
        private const val channel = "channel2"
    }

    fun build(context: Context): ToastService {
        this.context = context
        notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val headsUpView = RemoteViews(context.packageName, R.layout.pop_container)
        val intent = Intent()
        intent.setClass(context, BlankActivity::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notification = NotificationCompat.Builder(context, channel).apply {
            setSmallIcon(R.mipmap.ic_launcher)
//            setContentTitle("123")
//            setContentText("123")
//            setTicker("")
            setFullScreenIntent(pendingIntent, true)
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE or NotificationCompat.DEFAULT_LIGHTS)
            setStyle(NotificationCompat.DecoratedCustomViewStyle())
            setCustomContentView(headsUpView)
            setCustomHeadsUpContentView(headsUpView)
            setCategory(NotificationCompat.CATEGORY_CALL)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            }
        }.build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channel,
                    "toast",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    enableLights(true)
                })
        }
        return this
    }

    fun show() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationManager.notify((Math.random() * 100).toInt(), notification)
        }
    }


}