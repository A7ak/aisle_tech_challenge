package com.example.aisletechchallenge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aisletechchallenge.databinding.ActivityPhoneNumberBinding

class PhoneNumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            navigateToOtpScreen()
        }
    }

    private fun navigateToOtpScreen() {
        var intent = Intent(this@PhoneNumberActivity, OtpActivity::class.java)
        startActivity(intent)
    }
}