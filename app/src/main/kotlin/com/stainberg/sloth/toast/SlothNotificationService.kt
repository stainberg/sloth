package com.stainberg.sloth.toast

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.stainberg.sloth.R
import com.stainberg.sloth.ui.activity.BlankActivity


class SlothNotificationService {

    private lateinit var context: Context
    private lateinit var notification: Notification
    private lateinit var notificationManager: NotificationManager

    companion object {
        private const val channel = "channel2"
        private const val name = "notification"
        private const val requestCode = 1000
    }

    fun build(context: Context): SlothNotificationService {
        this.context = context
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val headsUpView = RemoteViews(context.packageName, R.layout.pop_container)
        val intent = Intent()
        intent.setClass(context, BlankActivity::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channel,
                    name,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    enableLights(true)
                })
        }
        notification = NotificationCompat.Builder(context, channel).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setFullScreenIntent(pendingIntent, true)
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            setDefaults(NotificationCompat.DEFAULT_SOUND or NotificationCompat.DEFAULT_VIBRATE or NotificationCompat.DEFAULT_LIGHTS)
            setStyle(NotificationCompat.DecoratedCustomViewStyle())
            setCustomContentView(headsUpView)
            setCustomHeadsUpContentView(headsUpView)
            setCategory(NotificationCompat.CATEGORY_CALL)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            }

        }.build()

        return this
    }

    fun show() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationManager.notify((Math.random() * 100).toInt(), notification)
        }
    }


}