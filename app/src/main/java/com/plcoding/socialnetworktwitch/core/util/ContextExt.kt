package com.plcoding.socialnetworktwitch.core.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.plcoding.socialnetworktwitch.R

fun Context.sendSharePostIntent(postId: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            getString(
                R.string.share_intent_text,
                "https://pl-coding.com/$postId"
            )
        )
        type = "text/plain"
    }

    if(intent.resolveActivity(packageManager) != null) {
        startActivity(Intent.createChooser(intent, "Select an app"))
    }
}