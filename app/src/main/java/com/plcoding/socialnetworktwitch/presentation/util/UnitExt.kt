package com.plcoding.socialnetworktwitch.presentation.util

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.unit.Dp

fun Dp.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.value,
        Resources.getSystem().displayMetrics
    )
}