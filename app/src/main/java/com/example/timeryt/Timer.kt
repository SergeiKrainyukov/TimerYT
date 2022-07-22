package com.example.timeryt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.timeryt.ui.theme.Purple500
import kotlinx.coroutines.delay

private val TIMER_SIZE = 200.dp

private const val START_ANGLE = -90f
private const val STROKE_WIDTH = 15f
private const val INITIAL_SWEEP_ANGLE = 350f

@Composable
fun Timer(modifier: Modifier) {

    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    var sweepAngle by remember {
        mutableStateOf(INITIAL_SWEEP_ANGLE)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(key1 = sweepAngle, key2 = isTimerRunning) {
            if (sweepAngle > 0 && isTimerRunning) {
                delay(100L)
                sweepAngle -= 50
            }
        }

        Canvas(modifier = modifier) {
            drawArc(
                color = Purple500,
                startAngle = START_ANGLE,
                sweepAngle = sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(width = STROKE_WIDTH, cap = StrokeCap.Round)
            )
        }
        Button(
            onClick = {
                if (isTimerRunning) sweepAngle = INITIAL_SWEEP_ANGLE
                isTimerRunning = !isTimerRunning
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isTimerRunning) {
                    Purple500
                } else {
                    Color.Red
                }
            )
        ) {
            Text(text = stringResource(id = if (isTimerRunning) R.string.button_stop else R.string.button_start))
        }
    }
}

@Preview
@Composable
fun Preview() {
    Timer(modifier = Modifier.size(TIMER_SIZE))
}