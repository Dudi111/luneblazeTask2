package com.example.luneblaze.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.luneblaze.R
import com.example.luneblaze.UI.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import okhttp3.internal.notify
import java.util.*
import kotlin.random.Random

class Firebaseservice: FirebaseMessagingService() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent= Intent(this, MainActivity::class.java)

        val notifymanager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifyId= Random.nextInt()


            createnotificationchannel(notifymanager)


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent= PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val notification= NotificationCompat.Builder(this, "my_channel")
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.ic_baseline_face_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notifymanager.notify(notifyId, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createnotificationchannel(notimanager:NotificationManager){

        val channelName= "ChannelName"
        val channel= NotificationChannel("my_channel",channelName, IMPORTANCE_HIGH).apply {
            description= "My Channel Disp"
            enableLights(true)
            lightColor= Color.GREEN
        }
        notimanager.createNotificationChannel(channel)
    }
}