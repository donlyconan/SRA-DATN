package com.donly.funny.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class RegistrationViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    @Throws(SignUpException::class)
    fun newUserRegistration(): Boolean {
        if(password.value == confirmPassword.value && password.value != null) {
            // TODO
        }
        return false
    }

    class SignUpException(val errorCode: Int) : Exception() {

        companion object {
            const val ERROR_PASSWORD = 1
            const val ERROR_CONFIRM_PASSWORD = 2
            const val ERROR_EMAIL = 3
        }

    }
}