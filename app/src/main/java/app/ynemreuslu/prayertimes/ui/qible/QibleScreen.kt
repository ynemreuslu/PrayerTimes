package app.ynemreuslu.prayertimes.ui.qible

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlin.math.roundToInt
import app.ynemreuslu.prayertimes.R

@Composable
fun QibleScreen(uiState: QibleContract.UiState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TextView for Direction
        Text(
            text = "${uiState.rotation?.roundToInt()}°",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
        val qiblaDirectionDegrees  = uiState.qiblaDirection ?: 0f
        val rotationDegrees  = uiState.rotation ?: 0f

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_compass_direction),
                contentDescription = "Compass",
                modifier = Modifier.fillMaxSize().rotate(rotationDegrees)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_qibla_needle),
                contentDescription = "Needle",
                modifier = Modifier
                    .size(200.dp)
                    .height(200.dp)
                    .width(200.dp)
                    .rotate(qiblaDirectionDegrees - rotationDegrees)
                    .aspectRatio(1f)
            )
        }

        Text(
            text = "Kıble Yönü: ${uiState.qiblaDirection?.roundToInt()}°",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}
