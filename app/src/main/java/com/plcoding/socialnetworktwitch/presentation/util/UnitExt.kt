package com.plcoding.socialnetworktwitch.presentation.util

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Dp.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.value,
        Resources.getSystem().displayMetrics
    )
}

fun Float.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}