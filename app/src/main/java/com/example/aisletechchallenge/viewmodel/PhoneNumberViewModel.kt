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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun verifyPhoneNumber(userCredReq: UserCredReq) {
        launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitBuilder.apiService.verifyPhoneNumber(userCredReq = userCredReq)

                if (response.isSuccessful && response != null) {
                    response.body()?.let {
                        _message.value = it
                    }
                } else {
                    _error.value = "Something went wrong"
                }
            } catch (e: java.lang.Exception) {
                _error.value = e.message
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
                } else {
                    _error.value = "Something went wrong"
                }
            } catch (e: java.lang.Exception) {
                _error.value = e.message
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
                } else {
                    _error.value = "Something went wrong"
                }
            } catch (e: java.lang.Exception) {
                _error.value = e.message
            }
        }
    }
}