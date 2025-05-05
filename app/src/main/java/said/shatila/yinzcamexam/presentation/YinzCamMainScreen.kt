package said.shatila.yinzcamexam.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import said.shatila.yinzcamexam.YinzCamViewModel
import said.shatila.yinzcamexam.model.local.Game
import said.shatila.yinzcamexam.model.local.GameSection
import said.shatila.yinzcamexam.model.local.Schedule
import said.shatila.yinzcamexam.model.local.Team
import said.shatila.yinzcamexam.model.ByeList
import said.shatila.yinzcamexam.model.GamesList
import said.shatila.yinzcamexam.model.YinzCamUiState
import said.shatila.yinzcamexam.ui.theme.BackgroundColor

@Composable
fun YinzCamMainScreen(
    modifier: Modifier = Modifier,
    viewModel: YinzCamViewModel = hiltViewModel()
) {
    val uiState = viewModel.yinzCamUIState.collectAsStateWithLifecycle().value

    YinzCamContentView(
        modifier = modifier.fillMaxWidth(),
        uiState = uiState
    )
}


@Composable
private fun YinzCamContentView(
    modifier: Modifier = Modifier,
    uiState: YinzCamUiState,
) {
    LazyColumn(modifier = modifier) {
        when (uiState) {
            is YinzCamUiState.Error -> {
                item {
                    ErrorView(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                    )
                }
            }

            is YinzCamUiState.Loading -> {
                items(count = 5, key = { "skeleton_$it" }) {
                    GameViewSkeleton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(BackgroundColor)
                            .padding(16.dp)
                    )
                }
            }

            is YinzCamUiState.Success -> {
                val sections = uiState.data.gameSection.orEmpty()
                items(
                    items = sections,
                    key = { section -> section.uniqueId },
                ) { section ->
                    YinzCamBodyView(
                        gameSection = section,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(BackgroundColor)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(48.dp))
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
    YinzCamContentView(
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

