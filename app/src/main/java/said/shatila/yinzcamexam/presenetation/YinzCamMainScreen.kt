package said.shatila.yinzcamexam.presenetation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import said.shatila.yinzcamexam.YinzCamViewModel
import said.shatila.yinzcamexam.data.local.Game
import said.shatila.yinzcamexam.data.local.GameSection
import said.shatila.yinzcamexam.data.local.Schedule
import said.shatila.yinzcamexam.data.local.Team
import said.shatila.yinzcamexam.model.ByeList
import said.shatila.yinzcamexam.model.GamesList
import said.shatila.yinzcamexam.model.YinzCamUiState

@Composable
fun YinzCamMainScreen(
    modifier: Modifier = Modifier,
    viewModel: YinzCamViewModel = hiltViewModel()
) {
    val uiState = viewModel.yinzCamUIState.collectAsStateWithLifecycle().value

    YinzCamScheduleContentView(
        modifier = modifier,
        uiState = uiState
    )
}


@Composable
private fun YinzCamScheduleContentView(
    modifier: Modifier = Modifier,
    uiState: YinzCamUiState,
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        item {
            when (uiState) {
                is YinzCamUiState.Error -> {
                    //Error
                }

                is YinzCamUiState.Loading -> {
                    repeat(5) {
                        GameViewSkeleton()
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }

                is YinzCamUiState.Success -> {
                    uiState.data.gameSection?.forEach { gameSection ->
                        YinzCamBodyView(gameSection = gameSection)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(48.dp))
        }
    }
}


@Preview
@Composable
private fun YinzCamScheduleContentPreview() {
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
    YinzCamScheduleContentView(
        modifier = Modifier,
        YinzCamUiState.Success(
            data = Schedule(
                uniqueId = "1",
                team = Team(
                    name = "Green Bay",
                    record = "34",
                    teamLogoUrl = "https://via.placeholder.com/40"
                ),
                defaultGameId = 1,
                gameSection = List(2) {
                    GameSection(
                        uniqueId = "section1",
                        heading = "Regular Season",
                        games = listOf(
                            GamesList(finalGame.uniqueId, finalGame),
                            GamesList(scheduledGame.uniqueId, scheduledGame),
                            ByeList(byeGame.uniqueId, byeGame)
                        )
                    )
                    GameSection(
                        uniqueId = "section12",
                        heading = "Regular Season 2",
                        games = listOf(
                            GamesList(finalGame.uniqueId, finalGame),
                            GamesList(scheduledGame.uniqueId, scheduledGame),
                            ByeList(byeGame.uniqueId, byeGame)
                        )
                    )
                }
            )
        )
    )
}

