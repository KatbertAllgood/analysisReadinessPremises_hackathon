package com.example.data.network.api

import com.example.data.models.LoginParamsData
import com.example.data.models.LoginResponseData
import com.example.data.models.ObjectsToDateResponseData
import com.example.data.models.RegistrationParamsData
import com.example.data.models.SendVideoParamsRequestData
import com.example.data.models.SendVideoParamsResponseData
import com.example.data.models.ServerResponseMessageData
import com.example.data.models.UserParamsData
import com.example.domain.utils.Constants
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ServerApi {

    companion object {
        operator fun invoke() : ServerApi{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_VIDEO)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServerApi::class.java)
        }
    }

    //region VIDEO_URL

    @Multipart
    @POST("upload")
    fun uploadVideo(
        @Part video: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    ) : Call<SendVideoParamsResponseData>

    @GET("excel_files/latest")
    fun downloadFile() : Call<ResponseBody>

    //endregion


    @POST("register")
    fun register(
        @Body registerParams : RegistrationParamsData
    ) : Single<ServerResponseMessageData>

    @POST("login")
    fun login(
        @Body loginParams : LoginParamsData
    ) : Single<LoginResponseData>

    @GET("users/{id}")
    fun getUserById(
        @Path("id") id : String,
    ) : Single<UserParamsData>

    @GET("objects")
    fun getAllObjects() : Single<List<ObjectsToDateResponseData>>

}