package com.plcoding.socialnetworktwitch.core.presentation.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun AnnotatedClickableText(

) {
    val annotatedText = buildAnnotatedString {
        //Start of the pushing annotation which you want to color and make them clickable later
        pushStringAnnotation(
            tag = "username",// provide tag which will then be provided when you click the text
            annotation = "username"
        )
        //add text with your different color/style
        withStyle(
            style = SpanStyle(
                color = Color.Red,
            )
        ) {
            append("Sign Up")
        }

        //append your initial text
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold)
        ) {
            append("Florian")

        }


        // when pop is called it means the end of annotation with current tag
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "SignUp",// tag which you used in the buildAnnotatedString
                start = offset,
                end = offset
            ).firstOrNull()?.let { annotation ->
                //do your stuff when it gets clicked
                Log.d("Clicked", annotation.item)
            }
        }
    )
}