package com.example.data.repository

import com.example.data.mappers.LoginParamsToData
import com.example.data.mappers.LoginResponseToDomain
import com.example.data.mappers.RegistrationParamsToData
//import com.example.data.mappers.SendVideoParamsRequestToData
//import com.example.data.mappers.SendVideoParamsRequestToDomain
import com.example.data.mappers.SendVideoParamsResponseToDomain
import com.example.data.mappers.ServerResponseMessageToDomain
import com.example.data.network.api.NetworkService
import com.example.domain.models.LoginParamsDomain
import com.example.domain.models.LoginResponseDomain
import com.example.domain.models.RegistrationParamsDomain
import com.example.domain.models.SendVideoParamsRequestDomain
import com.example.domain.models.SendVideoParamsResponseDomain
import com.example.domain.models.ServerResponseMessageDomain
import com.example.domain.repository.NetworkRepository
import io.reactivex.Single

class NetworkRepositoryImpl : NetworkRepository {


//    override fun sendVideo(
//        video: SendVideoParamsRequestDomain
//    ): Single<SendVideoParamsResponseDomain> {
//        return NetworkService.retorfitVideoService.sendVideo(
//            SendVideoParamsRequestToData(video).toData()
//        ).map {
//            return@map SendVideoParamsResponseToDomain(it).toDomain()
//        }
//    }

    override fun register(registerParams: RegistrationParamsDomain): Single<ServerResponseMessageDomain> {
        return NetworkService.retrofitService.register(
            RegistrationParamsToData(registerParams).toData()
        ).map {
            return@map ServerResponseMessageToDomain(it).toDomain()
        }
    }

    override fun login(loginParams: LoginParamsDomain): Single<LoginResponseDomain> {
        return NetworkService.retrofitService.login(
            LoginParamsToData(loginParams).toData()
        ).map {
            return@map LoginResponseToDomain(it).toDomain()
        }
    }


}