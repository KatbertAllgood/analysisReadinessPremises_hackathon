package com.example.samolethackaton.screens.mainpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ObjectParamsDomain
import com.example.domain.models.ObjectsToDateResponseDomain
import com.example.domain.usecase.objects.GetAllObjectsUseCase
import com.example.samolethackaton.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPageFragmentVM : ViewModel() {

    private val TAG = MainPageFragmentVM::class.simpleName

    private val networkRepository = App.getNetworkRepository()
    private val getAllObjectsUseCase = GetAllObjectsUseCase(networkRepository)

    private var allObjects : List<ObjectsToDateResponseDomain> = listOf()

    private val tasksOnSelectedDate = MutableLiveData<List<ObjectParamsDomain>>()
    fun getTasksOnSelectedDateLiveData() : LiveData<List<ObjectParamsDomain>> = tasksOnSelectedDate

    private val allDates = MutableLiveData<List<Pair<String, String>>>()
    fun getAllDateLiveData() : LiveData<List<Pair<String, String>>> = allDates

    fun getAllObjects() {
        getAllObjectsUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<ObjectsToDateResponseDomain>>(){
                override fun onSuccess(t: List<ObjectsToDateResponseDomain>) {
                    Log.d(TAG, "GET ALL OBJECTS SUCCESS: $t")

                    allObjects = t

//                    val dateSet : MutableSet<String> = mutableSetOf()
                    val dates : MutableList<Pair<String, String>> = mutableListOf()
//                    val datesResult : MutableList<Pair<String, String>> = mutableListOf()

                    for (i in t) {
//                        dateSet.add(i.date)
                        dates.add(Pair(i.date, i.objects.size.toString()))
                    }

                    allDates.value = dates

//                    changeDate(t[0].date)

                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "GET ALL OBJECTS ERROR: ${e.message}")
                }

            })
    }

    fun changeDate(date: String) {

        for (i in allObjects) {

            if (date == i.date) {

                tasksOnSelectedDate.value = i.objects

            }
        }
    }

}