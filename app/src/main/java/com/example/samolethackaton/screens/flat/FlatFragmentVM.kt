package com.example.samolethackaton.screens.flat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.utils.ApplicationPreferences
import com.example.domain.models.ScorRequestDomain
import com.example.domain.models.ScorResponseDomain
import com.example.domain.models.SectionParamsDomain
import com.example.domain.usecase.score.GetScoreUseCase
import com.example.samolethackaton.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FlatFragmentVM : ViewModel() {

    private val TAG = FlatFragmentVM::class.simpleName

    private val networkRepository = App.getNetworkRepository()
    private val getScoreUseCase = GetScoreUseCase(networkRepository)

    private val scoreLiveData = MutableLiveData<ScorResponseDomain>()
    fun getScoreLiveData() : LiveData<ScorResponseDomain> = scoreLiveData

    fun getScore() {

        val videoPath : String = ApplicationPreferences.videoPath ?: ""

        val request : ScorRequestDomain = ScorRequestDomain(videoPath)

        getScoreUseCase.invoke(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ScorResponseDomain>() {
                override fun onSuccess(t: ScorResponseDomain) {
                    Log.d(TAG, "SCOR SUCCESS: ${t.status_code} ${t.data}")
                    scoreLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "SCOR ERROR: ${e.message}")
                }

            })
    }

}