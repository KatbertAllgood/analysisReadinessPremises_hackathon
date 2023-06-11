package com.example.data.repository

import com.example.data.mappers.LoginParamsToData
import com.example.data.mappers.LoginResponseToDomain
import com.example.data.mappers.ObjectParamsToDomain
import com.example.data.mappers.ObjectsToDateResponseToDomain
import com.example.data.mappers.RegistrationParamsToData
import com.example.data.mappers.ScorRequestToData
import com.example.data.mappers.ScorResponseToDomain
import com.example.data.mappers.SectionParamsToDomain
//import com.example.data.mappers.SendVideoParamsRequestToData
//import com.example.data.mappers.SendVideoParamsRequestToDomain
import com.example.data.mappers.SendVideoParamsResponseToDomain
import com.example.data.mappers.ServerResponseMessageToDomain
import com.example.data.network.api.NetworkService
import com.example.domain.models.LoginParamsDomain
import com.example.domain.models.LoginResponseDomain
import com.example.domain.models.ObjectParamsDomain
import com.example.domain.models.ObjectsToDateResponseDomain
import com.example.domain.models.RegistrationParamsDomain
import com.example.domain.models.ScorRequestDomain
import com.example.domain.models.ScorResponseDomain
import com.example.domain.models.SectionParamsDomain
import com.example.domain.models.SendVideoParamsRequestDomain
import com.example.domain.models.SendVideoParamsResponseDomain
import com.example.domain.models.ServerResponseMessageDomain
import com.example.domain.repository.NetworkRepository
import io.reactivex.Single

class NetworkRepositoryImpl : NetworkRepository {

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

    override fun getAllObjects(): Single<List<ObjectsToDateResponseDomain>> {
        return NetworkService.retrofitService.getAllObjects().map {
            val list : MutableList<ObjectsToDateResponseDomain> = mutableListOf()
            for (i in it) {
                list.add(ObjectsToDateResponseToDomain(i).toDomain())
            }
            return@map list
        }
    }

    override fun getObjectById(id: String): Single<ObjectParamsDomain> {
        return NetworkService.retrofitService.getObjectById(id).map {
            return@map ObjectParamsToDomain(it).toDomain()
        }
    }

    override fun getAllSections(): Single<List<SectionParamsDomain>> {
        return NetworkService.retrofitService.getAllSections().map {
            val result: MutableList<SectionParamsDomain> = mutableListOf()
            for (i in it) {
                result.add(SectionParamsToDomain(i).toDomain())
            }
            return@map result
        }
    }

    override fun getScore(path: ScorRequestDomain): Single<ScorResponseDomain> {
        return NetworkService.retrofitSeviceScor.getScores(
            ScorRequestToData(path).toData()
        ).map {
            return@map ScorResponseToDomain(it).toDomain()
        }
    }


}