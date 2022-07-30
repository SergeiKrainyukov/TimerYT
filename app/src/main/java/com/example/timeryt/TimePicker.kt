package com.example.timeryt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.timeryt.model.FullTime

@Composable
fun CustomTimePicker(
    value: FullTime,
    hoursRange: Iterable<Int> = (0..23),
    minutesRange: Iterable<Int> = (0..59),
    secondsRange: Iterable<Int> = (0..59),
    onValueChange: (FullTime) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            value = value.hours,
            onValueChange = {
                onValueChange(value.copy(hours = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = hoursRange
        )

        TimeDivider(stringResource(id = R.string.hours))

        NumberPicker(
            value = value.minutes,
            onValueChange = {
                onValueChange(value.copy(minutes = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = minutesRange
        )

        TimeDivider(stringResource(id = R.string.minutes))

        NumberPicker(
            value = value.seconds,
            onValueChange = {
                onValueChange(value.copy(seconds = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = secondsRange
        )

        TimeDivider(stringResource(id = R.string.seconds))
    }
}

@Composable
fun TimeDivider(text: String) {
    Text(
        modifier = Modifier.padding(10.dp),
        textAlign = TextAlign.Center,
        text = text,
        style = TextStyle(color = Color.White)
    )
}
