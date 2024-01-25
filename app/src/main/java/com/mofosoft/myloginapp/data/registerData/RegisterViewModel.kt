package com.mofosoft.myloginapp.data.registerData

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mofosoft.myloginapp.data.registerData.RegisterUIEvent
import com.mofosoft.myloginapp.data.rules.Validator

class RegisterViewModel: ViewModel() {

    var registerUiState = mutableStateOf(RegisterUiState())

    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event : RegisterUIEvent){
        when(event){
            is RegisterUIEvent.EmailChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    email = event.email
                )
                validateData()
            }

            is RegisterUIEvent.PasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    new_password = event.new_password
                )
                validateData()
            }

            is RegisterUIEvent.ConfirmPasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    confirm_password = event.confirm_password
                )
                validateData()
            }

            is RegisterUIEvent.RegisterButtonClicked -> {
                //login to Register a new user after validation
                RegisterUser()
            }
        }
    }

    private fun RegisterUser() {
        createUserInFirebase(
            email = registerUiState.value.email,
            password = registerUiState.value.new_password
        )
    }

    private fun validateData() {
        val emailResult = Validator.validateEmail(
            email = registerUiState.value.email
        )

        val passwordResult = Validator.validateNewPassword(
            password = registerUiState.value.new_password
        )

        val ConfirmPasswordResult = Validator.validateConfirmPassword(
            password = registerUiState.value.new_password,
            confirmPassword = registerUiState.value.confirm_password
        )

        registerUiState.value = registerUiState.value.copy(
            emailError = emailResult.status,
            newPasswordError = passwordResult.status,
            confirmPasswordError = ConfirmPasswordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status && ConfirmPasswordResult.status
    }


    private fun createUserInFirebase(email : String, password : String){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                registerUiState.value = registerUiState.value.copy(
                    isRegistrationSuccessful = true
                )
            }
            .addOnFailureListener{
                registerUiState.value = registerUiState.value.copy(
                    errorMessage = it.message.toString()
                )
            }
    }
}