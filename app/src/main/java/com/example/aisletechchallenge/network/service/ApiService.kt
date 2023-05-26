package com.example.aisletechchallenge.network.service

import com.example.aisletechchallenge.model.NotesRes
import com.example.aisletechchallenge.model.OtpRes
import com.example.aisletechchallenge.model.PhoneNumberRes
import com.example.aisletechchallenge.model.UserCredReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("users/phone_number_login")
    suspend fun verifyPhoneNumber(@Body userCredReq: UserCredReq) : Response<PhoneNumberRes>

    @POST("users/verify_otp")
    suspend fun verifyOtp(@Body userCredReq: UserCredReq) : Response<OtpRes>

    @GET("users/test_profile_list")
    suspend fun getNotes(@Header("Authorization") token : String) : Response<NotesRes>

}