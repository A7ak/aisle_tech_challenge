package com.example.aisletechchallenge.ui.login.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aisletechchallenge.ui.home.activity.MainActivity
import com.example.aisletechchallenge.databinding.ActivityOtpBinding
import com.example.aisletechchallenge.model.UserCredReq
import com.example.aisletechchallenge.network.builder.RetrofitBuilder
import com.example.aisletechchallenge.repository.PhoneNumberRepository
import com.example.aisletechchallenge.ui.login.MyViewModelFactory
import com.example.aisletechchallenge.viewmodel.PhoneNumberViewModel

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding
    private lateinit var phoneNumberViewModel: PhoneNumberViewModel
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitBuilder.apiService
        val phoneNumberRepository = PhoneNumberRepository(retrofitService)
        phoneNumberViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(phoneNumberRepository)
        )[PhoneNumberViewModel::class.java]

        binding.tvPhoneNo.text = intent.extras?.getString("number")

        startTimer()

        binding.tvPhoneNo.setOnClickListener{
            finish()
        }

        binding.btContinue.setOnClickListener {
            if (!binding.etOtp.text.isNullOrEmpty()) {
                binding.progress.visibility = View.VISIBLE
                var userCredReq =
                    UserCredReq(
                        number = binding.tvPhoneNo.text.toString().replace(" ", ""),
                        otp = binding.etOtp.text.toString()
                    )
                phoneNumberViewModel.verifyOtp(userCredReq = userCredReq)
            } else {
                showToast("please enter otp")
            }
        }

        phoneNumberViewModel.error.observe(this) {
            binding.progress.visibility = View.GONE
            showToast(it)
        }

        phoneNumberViewModel.otpSuccess.observe(this) { data ->
            binding.progress.visibility = View.GONE
            if (!data.token.isNullOrEmpty()) {
                navigateToNotesScreen(data.token)
            } else {
                showToast("wrong otp")
            }
        }
    }

    private fun startTimer() {
        binding.timer.visibility = View.VISIBLE
        val time = 60 * 1000L
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(time: Long) {
                binding.timer.text = "00:${(time / 1000).toInt()}"
            }

            override fun onFinish() {
                binding.timer.visibility = View.GONE
            }
        }
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun navigateToNotesScreen(token: String) {
        var intent = Intent(this@OtpActivity, MainActivity::class.java)
        intent.putExtra("token", token)
        startActivity(intent)
    }
}