package com.example.aisletechchallenge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.aisletechchallenge.databinding.FragmentNotesBinding
import com.example.aisletechchallenge.viewmodel.PhoneNumberViewModel

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var phoneNumberViewModel: PhoneNumberViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        phoneNumberViewModel = ViewModelProvider(this)[PhoneNumberViewModel::class.java]

        var token = arguments?.getString("token")
        if (token != null) {
            binding.progress.visibility = View.VISIBLE
            phoneNumberViewModel.getNotes(token = token)
        }

        phoneNumberViewModel.notesSuccess.observe(viewLifecycleOwner) {data ->
            binding.progress.visibility = View.GONE
            showToast("notes data received")
        }

        return binding.root
    }

    private fun showToast(msg : String){
        Toast.makeText(context, msg , Toast.LENGTH_LONG).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}