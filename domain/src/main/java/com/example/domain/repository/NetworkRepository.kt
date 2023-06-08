package com.example.domain.repository

import android.telecom.Call
import com.example.domain.models.LoginParamsDomain
import com.example.domain.models.LoginResponseDomain
import com.example.domain.models.ObjectsToDateResponseDomain
import com.example.domain.models.RegistrationParamsDomain
import com.example.domain.models.SendVideoParamsRequestDomain
import com.example.domain.models.SendVideoParamsResponseDomain
import com.example.domain.models.ServerResponseMessageDomain
import io.reactivex.Single
import okhttp3.ResponseBody

interface NetworkRepository {

//    fun sendVideo(video: SendVideoParamsRequestDomain) : Single<SendVideoParamsResponseDomain>


    fun register(registerParams : RegistrationParamsDomain) : Single<ServerResponseMessageDomain>

    fun login(loginParams : LoginParamsDomain) : Single<LoginResponseDomain>

    fun getAllObjects() : Single<List<ObjectsToDateResponseDomain>>

}