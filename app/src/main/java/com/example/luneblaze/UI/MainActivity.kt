package com.example.luneblaze.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.luneblaze.R
import com.example.luneblaze.fcmdata.NotificationData
import com.example.luneblaze.fcmdata.PushNotification
import com.example.luneblaze.network.fcmretroInst
import com.example.luneblaze.repo.mainrepo
import com.example.luneblaze.viewmodel.mainViewModel
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC= "/topic/myTopic"
class MainActivity : AppCompatActivity() {

    private lateinit var repo_blaze:mainrepo
    private lateinit var viewmodel: mainViewModel
    private var imageurl:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repo_blaze= mainrepo()
        viewmodel= getViewModel()

        viewmodel.getapidata()

        viewmodel.liveData.observe(this){
            tv_username_main.text= it.user_fullname

            imageurl= it.user_picture
            Glide.with(applicationContext).load("https://luneblaze.com/new/Luneblaze-API/api/content/uploads/photos/2019/05/luneblaze_2bca86f9e699da684ef8dd9e10086cec.jpg").centerCrop().into(iv_user)
            tv_article.text= it.article_name
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        tv_informative.setOnClickListener {
            val title= "FirstNoti"
            val message= " Praveens Notification"
            if (title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title, message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }




    }

    private fun getViewModel(): mainViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return mainViewModel(repo_blaze) as T
            }
        })[mainViewModel::class.java]
    }

    private fun sendNotification(notifications: PushNotification){

        CoroutineScope(Dispatchers.Main).launch {
            val resp = fcmretroInst.fcmapi.fcmnotify(notifications)

            if (resp.isSuccessful) {
                Toast.makeText(this@MainActivity, "Notification send", Toast.LENGTH_SHORT).show()
            //    Log.d("myresp1", "Response: ${Gson().toJson(resp)}")
            } else {
                Toast.makeText(this@MainActivity, "Notification failed", Toast.LENGTH_SHORT).show()
             //   Log.d("myresp1", resp.errorBody().toString())

            }
        }
    }
}