package com.mofosoft.myloginapp.data.registerData

sealed class RegisterUIEvent{
    data class EmailChanged(val email : String) : RegisterUIEvent()
    data class PasswordChanged(val new_password : String) : RegisterUIEvent()
    data class ConfirmPasswordChanged(val confirm_password : String) : RegisterUIEvent()
    object RegisterButtonClicked : RegisterUIEvent()
}