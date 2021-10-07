package com.plcoding.socialnetworktwitch.core.domain.use_case

import android.content.SharedPreferences
import com.plcoding.socialnetworktwitch.core.util.Constants

class GetOwnUserIdUseCase(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        return (sharedPreferences.getString(Constants.KEY_USER_ID, "") ?: "")
    }
}