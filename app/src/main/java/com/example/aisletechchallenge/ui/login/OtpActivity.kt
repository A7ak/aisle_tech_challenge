package com.example.aisletechchallenge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aisletechchallenge.MainActivity
import com.example.aisletechchallenge.databinding.ActivityOtpBinding
import com.example.aisletechchallenge.model.UserCredReq
import com.example.aisletechchallenge.viewmodel.PhoneNumberViewModel

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    private lateinit var phoneNumberViewModel: PhoneNumberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phoneNumberViewModel = ViewModelProvider(this)[PhoneNumberViewModel::class.java]

        binding.btContinue.setOnClickListener {
            if (!binding.etOtp.text.isNullOrEmpty()) {
                var userCredReq = UserCredReq(number = "+919876543212", otp = binding.etOtp.text.toString())
                phoneNumberViewModel.verifyOtp(userCredReq = userCredReq)
            } else {
                // show error msg
            }
        }

        phoneNumberViewModel.otpSuccess.observe(this) { data ->
            if (!data.token.isNullOrEmpty()) {
                navigateToNotesScreen(data.token)
            }
        }
    }

    private fun navigateToNotesScreen(token: String) {
        var intent = Intent(this@OtpActivity, MainActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }
}