package com.mofosoft.myloginapp.data.registerData

data class RegisterUiState(
    var email : String = "",
    var new_password : String = "",
    var confirm_password: String = "",

    var emailError : Boolean = false,
    var newPasswordError : Boolean = false,
    var confirmPasswordError : Boolean = false,

    var isRegistrationSuccessful :Boolean = false,
    var errorMessage : String = ""
)
