package com.example.aisletechchallenge.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
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
            var phoneNumber :String = binding.etPhoneNo.text.toString()
            var countryCode : String = binding.etCountryCode.text.toString()

            if (!countryCode.isNullOrEmpty()) {
                if (!phoneNumber.isNullOrEmpty()) {
                binding.progress.visibility = View.VISIBLE
                callVerifyPhoneNumberAPI(countryCode+phoneNumber)
            } else {
                showToast("please enter phone number")
            }
        } else {
                showToast("please enter country code")
            }
        }
    }
    private fun callVerifyPhoneNumberAPI(number: String) {
        var userCredReq = UserCredReq(number = number, otp = null)
        phoneNumberViewModel.verifyPhoneNumber(userCredReq)
    }

    private fun showToast(msg : String){
        Toast.makeText(this, msg , Toast.LENGTH_LONG).show()
    }
    private fun navigateToOtpScreen() {
        var intent = Intent(this@PhoneNumberActivity, OtpActivity::class.java)
        intent.putExtra(
            "number",
            binding.etCountryCode.text.toString()+" "+ binding.etPhoneNo.text.toString()
        )
        startActivity(intent)
    }
}