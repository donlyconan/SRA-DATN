package com.donly.funny.views.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.donly.funny.R
import com.donly.funny.databinding.FragmentSignUpBinding
import com.donly.funny.viewmodels.RegistrationViewModel


class SignUpFragment : Fragment() {

    companion object {
       private const val TAG = "SignUpFragment"
    }

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        binding.handler = SignUpHandler()
        binding.model = registrationViewModel
        return binding.root
    }

    inner class SignUpHandler : View.OnClickListener{

        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.btn_create -> {
                    registrationViewModel.newUserRegistration()
                }
                R.id.txt_loginhere -> {
                    findNavController().navigateUp()
                }
                else -> {
                    Log.d(TAG, "onClick: viewId = " + v?.id)
                }
            }
        }
    }
}