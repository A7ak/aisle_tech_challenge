package com.example.aisletechchallenge.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aisletechchallenge.databinding.ActivityPhoneNumberBinding
import com.example.aisletechchallenge.model.UserCredReq
import com.example.aisletechchallenge.viewmodel.PhoneNumberViewModel

class PhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneNumberBinding
    private lateinit var phoneNumberViewModel: PhoneNumberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phoneNumberViewModel = ViewModelProvider(this)[PhoneNumberViewModel::class.java]

        phoneNumberViewModel.message.observe(this) {
            binding.progress.visibility = View.GONE
            navigateToOtpScreen()
        }

        binding.btContinue.setOnClickListener {
            if (!binding.etPhoneNo.text.isNullOrEmpty()) {
                binding.progress.visibility = View.VISIBLE
                callVerifyPhoneNumberAPI(binding.etPhoneNo.text.toString())
            } else {
                // show error msg
            }
        }
    }
    private fun callVerifyPhoneNumberAPI(number: String) {
        var userCredReq = UserCredReq(number = number, otp = null)
        phoneNumberViewModel.verifyPhoneNumber(userCredReq)
    }

    private fun navigateToOtpScreen() {
        var intent = Intent(this@PhoneNumberActivity, OtpActivity::class.java)
        startActivity(intent)
    }
}