package com.mofosoft.myloginapp.data.rules

import android.util.Patterns

object Validator {
    fun validateEmail(email : String) : ValidationResult {
        return ValidationResult(
            (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        )
    }
    fun validateNewPassword(password : String) : ValidationResult{
        return ValidationResult(
            (password.isNotEmpty() && password.length>=8)
        )
    }
    fun validateConfirmPassword(password : String, confirmPassword : String) : ValidationResult{
        return ValidationResult(
            (confirmPassword.isNotEmpty() && confirmPassword== password )
        )
    }
}

data class ValidationResult(
    val status : Boolean = false
)