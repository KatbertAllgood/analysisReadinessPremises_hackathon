package com.example.samolethackaton.screens.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.LoginParamsDomain
import com.example.domain.models.LoginResponseDomain
import com.example.domain.models.RegistrationParamsDomain
import com.example.domain.models.ServerResponseMessageDomain
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.samolethackaton.R
import com.example.samolethackaton.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AuthFragmentVM : ViewModel() {

    private val TAG = AuthFragmentVM::class.simpleName

    private val fragment = AuthFragment()

    private val networkRepository = App.getNetworkRepository()
    private val registerUseCase = RegisterUseCase(networkRepository)
    private val loginUseCase = LoginUseCase(networkRepository)

    private val onSuccessLoginLiveData = MutableLiveData<Boolean?>()
    fun getOnSuccessLogin() : LiveData<Boolean?> = onSuccessLoginLiveData

    fun registerUser(
        registerParams: RegistrationParamsDomain,
        context: Context
    ) {
        registerUseCase.invoke(registerParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ServerResponseMessageDomain>() {
                override fun onSuccess(t: ServerResponseMessageDomain) {
                    Log.d(TAG, "REGISTRATION_SUCCESS: ${t.message}")
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.register_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "REGISTRATION_ERROR: ${e.message}")
                }

            })
    }

    fun loginUser(
        loginParams: LoginParamsDomain,
        context: Context
    ) {
        loginUseCase.invoke(loginParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<LoginResponseDomain>() {
                override fun onSuccess(t: LoginResponseDomain) {
                    Log.d(TAG, "LOGIN_SUCCESS: ${t.token}")
//                    Toast.makeText(
//                        context,
//                        context.resources.getText(R.string.login_success),
//                        Toast.LENGTH_SHORT
//                    ).show()
                    onSuccessLoginLiveData.value = true

//                    fragment.navigateToMainPage()
                }
                override fun onError(e: Throwable) {
                    Log.d(TAG, "LOGIN_ERROR: ${e.message}")
                    onSuccessLoginLiveData.value = false
                }
            })

    }
}