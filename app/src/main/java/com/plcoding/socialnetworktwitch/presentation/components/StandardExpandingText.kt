package com.plcoding.socialnetworktwitch.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.plcoding.socialnetworktwitch.util.Constants.MAX_POST_DESCRIPTION_LINES
import com.plcoding.socialnetworktwitch.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun StandardExpandingText(modifier: Modifier = Modifier, text: String, align: TextAlign) {
	var isExpanded by remember { mutableStateOf(false) }
	val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
	var isClickable by remember { mutableStateOf(false) }
	var adjustedText by remember { mutableStateOf(text) }
	val textLayoutResult = textLayoutResultState.value
	val showMore = stringResource(id = R.string.show_more)
	val showLess = stringResource(id = R.string.show_less)
	LaunchedEffect(textLayoutResult) {
		if (textLayoutResult == null) return@LaunchedEffect
		if (!isExpanded && textLayoutResult.hasVisualOverflow) {
			val lastCharIndex = textLayoutResult.getLineEnd(MAX_POST_DESCRIPTION_LINES - 1)
			adjustedText = text
				.substring(startIndex = 0, endIndex = lastCharIndex)
				.dropLast(showMore.length)
				.dropLastWhile { it == ' ' || it == '.' }
			isClickable = true
		}
	}
	Text(
		text = buildAnnotatedString {
			if (isExpanded) {
				append("$text ")
				withStyle(
					style = SpanStyle(
						color = MaterialTheme.colors.secondaryVariant,
						fontSize = 14.sp,
						letterSpacing = TextUnit(0.0178571429f, TextUnitType.Sp)
					)
				) { append(showLess) }
			} else {
				append("$adjustedText ")
				withStyle(
					style = SpanStyle(
						color = MaterialTheme.colors.secondaryVariant,
						fontSize = 14.sp,
						letterSpacing = TextUnit(0.0178571429f, TextUnitType.Sp)
					)
				) { append(showMore) }
			}
		},
		style = MaterialTheme.typography.body1,
		textAlign = TextAlign.Justify,
		maxLines = if (isExpanded) Int.MAX_VALUE else MAX_POST_DESCRIPTION_LINES,
		onTextLayout = { textLayoutResultState.value = it },
		modifier = modifier
			.clickable(enabled = isClickable) { isExpanded = !isExpanded }
			.animateContentSize(),
	)
}