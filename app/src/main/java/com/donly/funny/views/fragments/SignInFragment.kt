package com.donly.funny.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.donly.funny.R
import com.donly.funny.databinding.FragmentSignInBinding
import com.donly.funny.viewmodels.LoginViewModel


class SignInFragment : Fragment() {
    companion object {
        private const val TAG: String = "SignInFragment"
    }
    private lateinit var binding: FragmentSignInBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        binding.handler = SignInHandler()
        binding.model = loginViewModel
        return binding.root
    }

    inner class SignInHandler : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.btn_login-> {
                    val action = SignInFragmentDirections.actionLoginFragmentToSignUpFragment()
                    findNavController().navigate(action)
                }
                R.id.txt_siginup -> {
                    val action = SignInFragmentDirections.actionLoginFragmentToSignUpFragment()
                    findNavController().navigate(action)
                }
                else -> {
                    Log.d(TAG, "onClick: viewId = " + v?.id)
                }
            }
        }
    }
}