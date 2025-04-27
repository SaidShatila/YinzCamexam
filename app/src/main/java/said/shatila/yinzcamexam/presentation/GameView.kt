package said.shatila.yinzcamexam.presentation

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import said.shatila.yinzcamexam.model.local.Game
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor
import said.shatila.yinzcamexam.ui.theme.SecondaryTextColor


@Composable
fun GameView(game: Game, modifier: Modifier = Modifier) {

    val awayHomeRecordTextStyle =
        if (game.isRecord) MaterialTheme.typography.bodySmall else MaterialTheme.typography.titleMedium


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier,
                text = game.homeTeamName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = PrimaryTextColor
            )
            Text(
                modifier = Modifier,
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
                modifier = Modifier,
                text = game.homeRecordScore,
                style = awayHomeRecordTextStyle.copy(
                    fontWeight = game.homeAwayRecordFontWeight
                ),
                color = game.homeAwayRecordColor
            )
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = game.homeTeamLogo,
                    contentDescription = game.homeTeamName,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    modifier = Modifier,
                    text = "@",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = SecondaryTextColor
                )
                AsyncImage(
                    model = game.opponentTeamLogo,
                    contentDescription = game.opponentTeamName,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                modifier = Modifier,
                text = game.awayRecordScore,
                style = awayHomeRecordTextStyle.copy(
                    fontWeight = game.homeAwayRecordFontWeight
                ),
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
            Text(
                modifier = Modifier,
                text = it,
                style = MaterialTheme.typography.bodySmall,
                color = PrimaryTextColor
            )
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