package said.shatila.yinzcamexam.presenetation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import said.shatila.yinzcamexam.data.local.Game
import said.shatila.yinzcamexam.ui.theme.BackgroundColor
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor
import said.shatila.yinzcamexam.ui.theme.SecondaryTextColor


@Composable
fun GameView(game: Game, modifier: Modifier = Modifier) {

    val textStyle = if (game.isRecord) {
        MaterialTheme.typography.bodySmall.copy(
            fontWeight = game.homeAwayRecordFontWeight
        )
    } else {
        MaterialTheme.typography.titleMedium.copy(
            fontWeight = game.homeAwayRecordFontWeight
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundColor),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = game.homeTeamName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = PrimaryTextColor
            )
            Text(
                text = game.opponentTeamName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = PrimaryTextColor
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = game.homeRecordScore,
                style = textStyle,
                color = game.homeAwayRecordColor
            )
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(game.opponentTeamLogo)
                        .memoryCacheKey(game.homeTeamName)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .diskCacheKey(game.homeTeamName)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .crossfade(true)
                        .build(),
                    contentDescription = game.homeTeamName,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = "@",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = SecondaryTextColor
                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(game.opponentTeamLogo)
                        .memoryCacheKey(game.opponentTeamName)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .diskCacheKey(game.opponentTeamName)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .crossfade(true)
                        .build(),
                    contentDescription = game.opponentTeamName,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = game.awayRecordScore,
                style = textStyle,
                color = game.homeAwayRecordColor
            )
        }

        Box(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = game.dateText,
                style = MaterialTheme.typography.bodySmall,
                color = PrimaryTextColor
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = game.week, style = MaterialTheme.typography.bodySmall,
                color = SecondaryTextColor,

                )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = game.gameStateDate,
                style = MaterialTheme.typography.bodySmall,
                color = PrimaryTextColor
            )
        }

        game.tvRadio?.takeIf {
            it.isNotEmpty()
        }?.also {
            Text(text = it, style = MaterialTheme.typography.bodySmall, color = PrimaryTextColor)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun GamePreview() {
    GameView(
        Game(
            id = 1,
            week = "Week 1",
            label = "@",
            gameStateDate = "10:00 AM",
            tvRadio = "TV",
            dateText = "Friday, 8, March",
            awayRecordScore = "110",
            homeAwayRecordColor = PrimaryTextColor,
            homeAwayRecordFontWeight = FontWeight.Bold,
            homeRecordScore = "100",
            homeTeamName = "Team 1",
            homeTeamLogo = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png",
            opponentTeamName = "Team 2",
            opponentTeamLogo = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png",
        )
    )
}