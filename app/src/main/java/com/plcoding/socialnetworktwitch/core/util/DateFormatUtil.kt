package com.plcoding.socialnetworktwitch.core.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {

    fun timestampToFormattedString(timestamp: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).run {
            format(timestamp)
        }
    }
}