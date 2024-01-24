package com.mofosoft.myloginapp.data

sealed class UIEvent {
    data class EmailChanged(val email : String) : UIEvent()
    data class PasswordChanged(val new_password : String) : UIEvent()
    data class ConfirmPasswordChanged(val confirm_password : String) : UIEvent()
    object RegisterButtonClicked : UIEvent()
}