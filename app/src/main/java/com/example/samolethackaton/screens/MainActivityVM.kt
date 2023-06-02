package com.example.samolethackaton.screens

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModel
import com.example.domain.models.SendVideoParamsRequestDomain
import com.example.domain.models.SendVideoParamsResponseDomain
import com.example.domain.utils.Constants
//import com.example.domain.usecase.video.SendVideoUseCase
import com.example.samolethackaton.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivityVM : ViewModel() {

    private val TAG = MainActivityVM::class.simpleName

    private val networkRepository = App.getNetworkRepository()

}