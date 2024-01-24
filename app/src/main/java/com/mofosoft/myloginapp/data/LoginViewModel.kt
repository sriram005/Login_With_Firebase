package com.mofosoft.myloginapp.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var registerUiState = mutableStateOf(RegisterUiState())

    fun onEvent(event : UIEvent){
        when(event){
            is UIEvent.EmailChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    email = event.email
                )
            }

            is UIEvent.PasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    new_password = event.new_password
                )
            }

            is UIEvent.ConfirmPasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    confirm_password = event.confirm_password
                )
            }

            is UIEvent.RegisterButtonClicked -> {
                //login to Register a new user after validation
            }
        }
    }
}