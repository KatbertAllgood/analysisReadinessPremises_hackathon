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

    private val progressOnSelectedDate = MutableLiveData<Int>()
    fun getProgressOnSelectedData() : LiveData<Int> = progressOnSelectedDate

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

                    val daysForFilter : MutableSet<Int> = mutableSetOf()
                    for (i in t) {
                        val day : Int = i.date.split(".")[0].toInt()
                        daysForFilter.add(day)
                    }

                    val sorterResponse : MutableList<ObjectsToDateResponseDomain> = mutableListOf()

                    for (i in daysForFilter.sorted()) {
                        for (j in t) {
                            if (i == j.date.split(".")[0].toInt()) {

                                sorterResponse.add(j)
                            }
                        }
                    }

                    val dates : MutableList<Pair<String, String>> = mutableListOf()

                    for (i in sorterResponse) {
                        dates.add(Pair(i.date, i.objects.size.toString()))
                    }

                    allDates.value = dates

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
                progressOnSelectedDate.value = i.progress

            }
        }
    }
}