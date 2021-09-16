package com.plcoding.socialnetworktwitch.feature_auth.domain.use_case

import android.util.Patterns
import com.plcoding.socialnetworktwitch.core.domain.util.ValidationUtil
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.feature_auth.domain.models.RegisterResult
import com.plcoding.socialnetworktwitch.feature_auth.domain.repository.AuthRepository
import com.plcoding.socialnetworktwitch.feature_auth.presentation.util.AuthError

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): RegisterResult {
        val emailError = ValidationUtil.validateEmail(email)
        val usernameError = ValidationUtil.validateUsername(username)
        val passwordError = ValidationUtil.validatePassword(password)

        if(emailError != null || usernameError != null || passwordError != null) {
            return RegisterResult(
                emailError = emailError,
                usernameError = usernameError,
                passwordError = passwordError,
            )
        }

        val result = repository.register(email.trim(), username.trim(), password.trim())

        return RegisterResult(
            result = result
        )
    }
}