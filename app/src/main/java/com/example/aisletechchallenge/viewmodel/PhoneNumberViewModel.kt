package com.example.aisletechchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aisletechchallenge.model.NotesRes
import com.example.aisletechchallenge.model.OtpRes
import com.example.aisletechchallenge.model.PhoneNumberRes
import com.example.aisletechchallenge.model.UserCredReq
import com.example.aisletechchallenge.network.builder.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class PhoneNumberViewModel : ViewModel(), CoroutineScope by MainScope() {

    private val _message = MutableLiveData<PhoneNumberRes>()
    val message: LiveData<PhoneNumberRes> get() = _message

    private val _otpSuccess = MutableLiveData<OtpRes>()
    val otpSuccess: LiveData<OtpRes> get() = _otpSuccess

    private val _notesSuccess = MutableLiveData<NotesRes>()
    val notesSuccess: LiveData<NotesRes> get() = _notesSuccess

    fun verifyPhoneNumber(userCredReq: UserCredReq) {
        launch(Dispatchers.Main) {
            try {
                val response = RetrofitBuilder.apiService.verifyPhoneNumber(userCredReq = userCredReq)

                if (response.isSuccessful && response != null) {
                    response.body()?.let {
                        _message.value = it
                    }
                }
            } catch (e: java.lang.Exception) {
                e.message
            }
        }
    }

    fun verifyOtp(userCredReq: UserCredReq) {
        launch(Dispatchers.Main) {
            try {
                val response = RetrofitBuilder.apiService.verifyOtp(userCredReq = userCredReq)

                if (response.isSuccessful && response != null) {
                    response.body()?.let {
                        _otpSuccess.value = it
                    }
                }
            } catch (e: java.lang.Exception) {
                e.message
            }
        }
    }

    fun getNotes(token: String) {
        launch(Dispatchers.Main) {
            try {
                val response = RetrofitBuilder.apiService.getNotes(token = token)

                if (response.isSuccessful && response != null) {
                    response.body()?.let {
                        _notesSuccess.value = it
                    }
                }
            } catch (e: java.lang.Exception) {
                e.message
            }
        }
    }
}