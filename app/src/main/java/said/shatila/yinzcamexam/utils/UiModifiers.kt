package said.shatila.yinzcamexam.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shimmer(
    shimmerWidth: Dp = 200.dp,
    durationMs: Int = 1000,
    shapeRadius: Dp = 12.dp
): Modifier = composed {
    val shimmerWidthPx = with(LocalDensity.current) { shimmerWidth.toPx() }

    val transition = rememberInfiniteTransition()
    val progress = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = remember(progress.value) {
        val startX = -shimmerWidthPx + 2 * shimmerWidthPx * progress.value
        val endX = startX + shimmerWidthPx
        Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f)
            ),
            start = Offset(startX, 0f),
            end = Offset(endX, 0f)
        )
    }

    then(
        this.clip(shape = RoundedCornerShape(shapeRadius)).background(brush)
    )

}