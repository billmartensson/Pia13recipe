package se.magictechnology.pia13recipe

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class RecipeMessaging : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d("PIA13debug", "WE GOT A NOTIFICATION")

        if (message.data.isNotEmpty()) {
            Log.d("PIA13debug", "Message data payload: ${message.data}")
        }

        message.notification?.let {
            Log.d("PIA13debug", "Message Notification Body: ${it.body}")
        }
    }

}