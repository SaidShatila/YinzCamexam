package said.shatila.yinzcamexam.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import said.shatila.yinzcamexam.model.local.Game
import said.shatila.yinzcamexam.model.local.GameSection
import said.shatila.yinzcamexam.model.ByeList
import said.shatila.yinzcamexam.model.GamesList
import said.shatila.yinzcamexam.ui.theme.BackgroundColor
import said.shatila.yinzcamexam.ui.theme.BackgroundGray50
import said.shatila.yinzcamexam.ui.theme.SecondaryTextColor

@Composable
fun YinzCamBodyView(
    modifier: Modifier = Modifier,
    gameSection: GameSection,
) {

    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(BackgroundGray50),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = gameSection.heading,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = SecondaryTextColor,
                textAlign = TextAlign.Center
            )
        }
        gameSection.games.forEachIndexed { index, game ->
            when (game) {
                is GamesList -> game.data?.let {
                    GameView(
                        game = it, modifier = Modifier
                            .fillMaxWidth()
                            .background(BackgroundColor)
                            .padding(16.dp)
                    )
                }

                is ByeList -> ByeView(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (index != gameSection.games.lastIndex)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun YinzCamBodyPreview() {
    val finalGame = Game(
        id = 1L,
        week = "Week 1",
        label = "1",
        gameStateDate = "Final",
        awayRecordScore = "43",
        homeRecordScore = "34",
        dateText = "Sep 13, 1:00 PM",
        homeTeamName = "Green Bay",
        homeTeamLogo = "https://via.placeholder.com/40",
        opponentTeamName = "Minnesota",
        opponentTeamLogo = "https://via.placeholder.com/40"
    )

    val scheduledGame = Game(
        id = 2L,
        week = "Week 2",
        label = "2",
        gameStateDate = "Upcoming",
        awayRecordScore = "",
        homeRecordScore = "",
        dateText = "Sep 20, 1:00 PM",
        homeTeamName = "Green Bay",
        homeTeamLogo = "https://via.placeholder.com/40",
        opponentTeamName = "Detroit",
        opponentTeamLogo = "https://via.placeholder.com/40"
    )

    val byeGame = Game(
        id = 3L,
        week = "Week 3",
        label = "3",
        gameStateDate = "Bye",
        awayRecordScore = "",
        homeRecordScore = "",
        dateText = "Bye Week",
        homeTeamName = "",
        homeTeamLogo = "",
        opponentTeamName = "",
        opponentTeamLogo = ""
    )

    YinzCamBodyView(
        gameSection = GameSection(
            uniqueId = "section1",
            heading = "Regular Season",
            games = listOf(
                GamesList(finalGame.uniqueId, finalGame),
                GamesList(scheduledGame.uniqueId, scheduledGame),
                ByeList(byeGame.uniqueId, byeGame)
            )
        )
    )
}