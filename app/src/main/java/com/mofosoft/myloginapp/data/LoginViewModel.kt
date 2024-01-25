package com.mofosoft.myloginapp.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mofosoft.myloginapp.data.rules.Validator

class LoginViewModel: ViewModel() {

    var registerUiState = mutableStateOf(RegisterUiState())

    fun onEvent(event : UIEvent){
        when(event){
            is UIEvent.EmailChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    email = event.email
                )
                validateData()
            }

            is UIEvent.PasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    new_password = event.new_password
                )
                validateData()
            }

            is UIEvent.ConfirmPasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    confirm_password = event.confirm_password
                )
                validateData()
            }

            is UIEvent.RegisterButtonClicked -> {
                //login to Register a new user after validation
                RegisterUser()
            }
        }
    }

    private fun RegisterUser() {
        validateData()
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
    }
}