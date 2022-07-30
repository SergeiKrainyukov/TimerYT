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

data class FullTime(
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0
) {
    fun toSeconds() = seconds + minutes * 60 + hours * 3600

    fun minusSecond() = fromSeconds(toSeconds() - 1)

    fun isDefault() = seconds == 0 && minutes == 0 && hours == 0

    companion object {
        fun fromSeconds(seconds: Int): FullTime {
            val hours = seconds / 3600
            val minutes = (seconds - 3600 * hours) / 60
            val secondsValue = seconds - hours * 3600 - minutes * 60
            return FullTime(
                hours = hours,
                minutes = minutes,
                seconds = secondsValue
            )
        }
    }
}

@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    value: FullTime,
    hoursRange: Iterable<Int> = (0..23),
    minutesRange: Iterable<Int> = (0..59),
    secondsRange: Iterable<Int> = (0..59),
    onValueChange: (FullTime) -> Unit,
    dividersColor: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Row(
        modifier = modifier,
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
