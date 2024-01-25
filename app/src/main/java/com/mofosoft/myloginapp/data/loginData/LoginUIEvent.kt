package com.mofosoft.myloginapp.data.loginData

sealed class LoginUIEvent{
    data class EmailChanged(val email : String) : LoginUIEvent()
    data class PasswordChanged(val new_password : String) : LoginUIEvent()
    object LoginButtonClicked : LoginUIEvent()
}