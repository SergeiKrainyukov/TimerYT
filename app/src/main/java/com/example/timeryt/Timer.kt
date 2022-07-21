package com.example.timeryt

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timeryt.ui.theme.Purple500

val TIMER_SIZE = 200.dp

const val START_ANGLE = -90f
const val SWEEP_ANGLE = 360f
const val STROKE_WIDTH = 15f

@Composable
fun Timer(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = Purple500,
                startAngle = START_ANGLE,
                sweepAngle = SWEEP_ANGLE,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(width = STROKE_WIDTH, cap = StrokeCap.Round)
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    Timer(modifier = Modifier.size(TIMER_SIZE))
}