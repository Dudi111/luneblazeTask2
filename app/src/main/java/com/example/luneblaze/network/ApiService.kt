package com.example.luneblaze.network

import com.example.luneblaze.datamodel.blazeModel
import com.example.luneblaze.fcmdata.Constants
import com.example.luneblaze.fcmdata.PushNotification
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("new/Luneblaze-API/api/app/react.json")
    suspend fun getSBlazeApi(
        @Field("user_id") user_id: String?,
        @Field("type") type: String?,
        @Field("content_id") content_id: String?,
        @Field("reaction") reaction: String?,
        @Field("user_type") user_type: String?

    ): blazeModel

    @Headers("Authorization: key=${Constants.server_key}", "Content-Type:${Constants.content_type}")
    @POST("fcm/send")
    suspend fun fcmnotify(
        @Body notification:PushNotification
    ):Response<ResponseBody>

}