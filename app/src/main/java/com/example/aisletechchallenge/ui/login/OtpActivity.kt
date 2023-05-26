package com.example.aisletechchallenge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aisletechchallenge.MainActivity
import com.example.aisletechchallenge.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            navigateToNotesScreen()
        }
    }

    private fun navigateToNotesScreen() {
        var intent = Intent(this@OtpActivity, MainActivity::class.java)
        startActivity(intent)
    }
}