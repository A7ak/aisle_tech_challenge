package com.example.aisletechchallenge.repository

import com.example.aisletechchallenge.model.UserCredReq
import com.example.aisletechchallenge.network.service.ApiService

class PhoneNumberRepository constructor(private val retrofitService: ApiService) {

    suspend fun verifyPhoneNumber(userCredReq: UserCredReq) =
        retrofitService.verifyPhoneNumber(userCredReq = userCredReq)

    suspend fun verifyOtp(userCredReq: UserCredReq) =
        retrofitService.verifyOtp(userCredReq = userCredReq)

    suspend fun getNotes(token: String) =
        retrofitService.getNotes(token = token)
}