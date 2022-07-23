package com.example.timeryt

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.chargemap.compose.numberpicker.NumberPicker

@Composable
fun CustomTimePicker(
    hours: Int,
    minutes: Int,
    seconds: Int,
    selectHours: (Int) -> Unit,
    selectMinutes: (Int) -> Unit,
    selectSeconds: (Int) -> Unit
) {
    Row {
        NumberPicker(
            value = hours,
            range = 0..10,
            onValueChange = selectHours,
            textStyle = TextStyle(color = Color.White)
        )
        NumberPicker(
            value = minutes,
            range = 0..10,
            onValueChange = selectMinutes,
            textStyle = TextStyle(color = Color.White)
        )
        NumberPicker(
            value = seconds,
            range = 0..10,
            onValueChange = selectSeconds,
            textStyle = TextStyle(color = Color.White)
        )
    }
}