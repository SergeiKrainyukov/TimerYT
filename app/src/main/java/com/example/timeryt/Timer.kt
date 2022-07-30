package com.example.timeryt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timeryt.ui.theme.GrayLight
import com.example.timeryt.ui.theme.Purple
import com.example.timeryt.ui.theme.White
import kotlinx.coroutines.delay
import java.sql.Time

private const val FULL_SWEEP_ANGLE = 360f
private val DEFAULT_TIMER_SIZE_DP = 400.dp

@Composable
fun Timer() {

    var timerState by remember {
        mutableStateOf(TimerState.STOPPED)
    }

    var sweepAngle by remember {
        mutableStateOf(FULL_SWEEP_ANGLE)
    }

    var startTime by remember { mutableStateOf(FullTime()) }

    var currentTime by remember { mutableStateOf(FullTime()) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (timerState) {
            TimerState.STOPPED -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(DEFAULT_TIMER_SIZE_DP)
                ) {
                    CustomTimePicker(
                        dividersColor = Purple,
                        value = startTime,
                        textStyle = TextStyle(color = White),
                        onValueChange = {
                            startTime = it
                        },
                    )
                }
            }
            TimerState.RUNNING -> {
                Box(contentAlignment = Alignment.Center) {
                    LaunchedEffect(key1 = sweepAngle) {
                        if (currentTime.isDefault()) {
                            sweepAngle = FULL_SWEEP_ANGLE
                            timerState = TimerState.STOPPED
                        }
                        if (sweepAngle > 0) {
                            delay(1000L)
                            currentTime = currentTime.minusSecond()
                            sweepAngle = calculateSweepAngle(currentTime, startTime)
                        }
                    }
                    TimerView(
                        sweepAngle = sweepAngle,
                        currentTime = currentTime,
                    )
                }
            }
            TimerState.PAUSED -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(DEFAULT_TIMER_SIZE_DP)
                ) {
                    TimerView(
                        sweepAngle = sweepAngle,
                        currentTime = currentTime,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(50.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            StartButton(timerState = timerState) {
                when (timerState) {
                    TimerState.RUNNING -> {
                        timerState = TimerState.PAUSED
                        return@StartButton
                    }
                    TimerState.PAUSED -> {
                        timerState = TimerState.RUNNING
                        return@StartButton
                    }
                    TimerState.STOPPED -> {
                        currentTime = startTime
                        timerState = TimerState.RUNNING
                        return@StartButton
                    }
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            StopButton {
                sweepAngle = FULL_SWEEP_ANGLE
                currentTime = FullTime()
                timerState = TimerState.STOPPED
            }
        }
    }
}

@Composable
fun StartButton(
    timerState: TimerState,
    onClick: () -> Unit
) {
    if (timerState == TimerState.RUNNING)
        Button(
            modifier = Modifier.size(height = 60.dp, width = 160.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = GrayLight),
        ) {
            Text(
                text = stringResource(id = R.string.button_pause),
                color = Color.White
            )
        } else Button(
        modifier = Modifier.size(height = 60.dp, width = 160.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (timerState == TimerState.RUNNING) {
                GrayLight
            } else {
                Purple
            }
        ),
    ) {
        Text(
            text = stringResource(id = if (timerState == TimerState.RUNNING) R.string.button_pause else R.string.button_start),
            color = Color.White
        )
    }
}

@Composable
fun StopButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.size(height = 60.dp, width = 160.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
    ) {
        Text(
            text = stringResource(id = R.string.button_stop),
            color = Color.White
        )
    }
}

@Composable
fun TimerView(
    sweepAngle: Float,
    currentTime: FullTime,
) {
    Canvas(
        modifier = Modifier
            .size(DEFAULT_TIMER_SIZE_DP)
            .padding(60.dp)
    ) {
        drawArc(
            color = GrayLight,
            startAngle = FULL_SWEEP_ANGLE,
            sweepAngle = FULL_SWEEP_ANGLE,
            useCenter = false,
            size = Size(size.width, size.height),
            style = Stroke(width = 20f, cap = StrokeCap.Round),
        )
        drawArc(
            color = Purple,
            startAngle = -FULL_SWEEP_ANGLE / 4,
            sweepAngle = sweepAngle,
            useCenter = false,
            size = Size(size.width, size.height),
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
    }
    Text(
        text = currentTime.toString(),
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

private fun calculateSweepAngle(currentTime: FullTime, startTime: FullTime) =
    FULL_SWEEP_ANGLE * currentTime.toSeconds() / startTime.toSeconds()

@Preview
@Composable
fun Preview() {
    Timer()
}