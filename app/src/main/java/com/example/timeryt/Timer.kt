package com.example.timeryt

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeryt.ui.theme.GrayLight
import com.example.timeryt.ui.theme.Purple
import kotlinx.coroutines.delay

@Composable
fun Timer(modifier: Modifier, totalTime: Float) {

    val startAngle = -90f
    val strokeWidth = 20f
    val initialSweepAngle = 350f

    var isTimerRunning by rememberSaveable {
        mutableStateOf(false)
    }

    var sweepAngle by rememberSaveable {
        mutableStateOf(initialSweepAngle)
    }

    var currentTime by rememberSaveable {
        mutableStateOf(totalTime)
    }

    Column {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            LaunchedEffect(key1 = sweepAngle, key2 = isTimerRunning) {
                if (sweepAngle > 0 && isTimerRunning) {
                    delay(1000L)
                    currentTime -= 1L
                    sweepAngle = initialSweepAngle * currentTime / totalTime
                }
            }

            Canvas(modifier = modifier.padding(60.dp)) {
                drawArc(
                    color = GrayLight,
                    startAngle = 360f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = Size(size.width, size.height),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                )
                drawArc(
                    color = Purple,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    size = Size(size.width, size.height),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
            Text(
                text = currentTime.toInt().toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(100.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.size(height = 60.dp, width = 160.dp),
                onClick = {
                    if (isTimerRunning) {
                        sweepAngle = initialSweepAngle
                        currentTime = totalTime
                    }
                    isTimerRunning = !isTimerRunning
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (!isTimerRunning) {
                        Purple
                    } else {
                        Color.Red
                    }
                ),
            ) {
                Text(text = stringResource(id = if (isTimerRunning) R.string.button_stop else R.string.button_start))
            }
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                modifier = Modifier
                    .size(height = 60.dp, width = 150.dp)
                    .border(
                        width = 2.dp,
                        color = GrayLight,
                        shape = RoundedCornerShape(15.dp)
                    ),
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = GrayLight),
            ) {
                Text(
                    text = stringResource(id = if (isTimerRunning) R.string.button_pause else R.string.button_pause),
                    color = Color.White
                )
            }
        }

    }
}

@Preview
@Composable
fun Preview() {
    Timer(modifier = Modifier.size(200.dp), 6f)
}