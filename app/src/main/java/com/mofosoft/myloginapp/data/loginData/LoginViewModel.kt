package com.mofosoft.myloginapp.data.loginData

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mofosoft.myloginapp.data.rules.Validator

class LoginViewModel : ViewModel() {

    var loginUiState = mutableStateOf(LoginUiState())
    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event : LoginUIEvent){
        when(event){
            is LoginUIEvent.EmailChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
                validateData()
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.new_password
                )
                validateData()
            }

            is LoginUIEvent.LoginButtonClicked -> {
                LoginUser()
            }
        }
    }

    private fun validateData() {
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }
    private fun LoginUser() {
        LoginUsingFirebase(
            email = loginUiState.value.email,
            password = loginUiState.value.password
        )
    }

    private fun LoginUsingFirebase(email: String, password: String) {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                loginUiState.value = loginUiState.value.copy(
                    isLoginSuccessful = true
                )
            }
            .addOnFailureListener {
                loginUiState.value = loginUiState.value.copy(
                    errorMessage = it.message.toString()
                )
            }
    }
}