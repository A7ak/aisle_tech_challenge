package com.example.aisletechchallenge.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aisletechchallenge.repository.PhoneNumberRepository
import com.example.aisletechchallenge.viewmodel.PhoneNumberViewModel

class MyViewModelFactory constructor(private val repository: PhoneNumberRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PhoneNumberViewModel::class.java)) {
            PhoneNumberViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}