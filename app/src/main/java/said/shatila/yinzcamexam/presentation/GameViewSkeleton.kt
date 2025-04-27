package said.shatila.yinzcamexam.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import said.shatila.yinzcamexam.utils.shimmer


@Composable
fun GameViewSkeleton(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .width(120.dp)
                    .height(20.dp)
                    .shimmer(200.dp, 1000)
            )
            Box(
                Modifier
                    .width(120.dp)
                    .height(20.dp)
                    .shimmer(200.dp, 1000)
            )
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .shimmer(200.dp, 1000)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    Modifier
                        .size(40.dp)
                        .shimmer(200.dp, 1000)
                )
                Box(
                    Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .shimmer(200.dp, 1000)
                )
                Box(
                    Modifier
                        .size(40.dp)
                        .shimmer(200.dp, 1000)
                )
            }

            Box(
                Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .shimmer(200.dp, 1000)
            )
        }

        Box(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .align(Alignment.TopStart)
                    .width(80.dp)
                    .height(16.dp)
                    .shimmer(200.dp, 1000)
            )
            Box(
                Modifier
                    .align(Alignment.Center)
                    .width(60.dp)
                    .height(16.dp)
                    .shimmer(200.dp, 1000)

            )
            Box(
                Modifier
                    .align(Alignment.TopEnd)
                    .width(80.dp)
                    .height(16.dp)
                    .shimmer(200.dp, 1000)

            )
        }

        Box(
            Modifier
                .fillMaxWidth(0.6f)
                .height(16.dp)
                .shimmer(200.dp, 1000)
        )

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@Preview
@Composable
private fun GameViewSkeletonPreview() {
    GameViewSkeleton()
}