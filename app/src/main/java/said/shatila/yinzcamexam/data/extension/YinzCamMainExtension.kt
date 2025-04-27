package said.shatila.yinzcamexam.data.extension

import androidx.compose.ui.text.font.FontWeight
import said.shatila.yinzcamexam.R
import said.shatila.yinzcamexam.model.local.Game
import said.shatila.yinzcamexam.model.local.GameSection
import said.shatila.yinzcamexam.model.local.Schedule
import said.shatila.yinzcamexam.model.local.Team
import said.shatila.yinzcamexam.model.remote.GameDTO
import said.shatila.yinzcamexam.model.remote.ScheduleDTO
import said.shatila.yinzcamexam.model.remote.TeamDTO
import said.shatila.yinzcamexam.model.ByeList
import said.shatila.yinzcamexam.model.GameResult
import said.shatila.yinzcamexam.model.GamesList
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor
import said.shatila.yinzcamexam.ui.theme.SecondaryTextColor
import said.shatila.yinzcamexam.utils.DateHelpers
import java.util.UUID

fun ScheduleDTO.toSectionUIModels(): Schedule {
    return Schedule(
        team = Team(
            team?.name.orEmpty(), team?.record.orEmpty(), team?.triCode.orEmpty()
        ),
        defaultGameId = defaultGameId,
        gameSection = toGameSectionList()
    )
}

fun ScheduleDTO.toGameSectionList(): List<GameSection> {
    val sections = gameSections ?: return emptyList()
    return sections.map { section ->
        GameSection(
            heading = section.heading.orEmpty(),
            games = section.games?.map { dto ->
                val game = team?.let { dto.toGame(it, dto.type.equals(GameResult.FINAL.name)) }

                when (dto.type) {
                    GameResult.FINAL.gameType->
                        GamesList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = game
                        )

                    GameResult.BYE.gameType ->
                        ByeList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = null
                        )

                    GameResult.SCHEDULED.gameType ->
                        GamesList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = game
                        )

                    else ->
                        GamesList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = game
                        )
                }
            } ?: emptyList()
        )
    }
}

fun GameDTO.toGame(homeTeam: TeamDTO, isFinal: Boolean): Game {
    return Game(
        id = id ?: 0,
        week = week.orEmpty(),
        label = label.orEmpty(),
        gameStateDate = if (isFinal) gameState.orEmpty() else DateHelpers.formatDateTime(
            date = date?.timestamp.orEmpty(),
            outputPattern = "hh:mm a"
        ),
        tvRadio = (tv?.takeIf { it.isNotEmpty() } ?: radio.orEmpty()),
        isRecord = !isFinal,
        awayRecordScore = if (isFinal) awayScore.orEmpty() else opponent?.record.orEmpty(),
        homeRecordScore = if (isFinal) homeScore.orEmpty() else homeTeam.record.orEmpty(),
        homeAwayRecordColor = if (isFinal) PrimaryTextColor else SecondaryTextColor,
        homeAwayRecordFontWeight = if (isFinal) FontWeight.Bold else FontWeight.Normal,
        dateText = DateHelpers.formatDateTime(date?.text.orEmpty(), outputPattern = "EEE, MMM dd"),
        homeTeamName = homeTeam.fullName.orEmpty(),
        homeTeamLogo = triCodeToLogoUrl(homeTeam.triCode)
            ?: R.drawable.baseline_sports_football_24,
        opponentTeamName = opponent?.name.orEmpty(),
        opponentTeamLogo = triCodeToLogoUrl(opponent?.triCode)
            ?: R.drawable.baseline_sports_football_24
    )
}

private fun triCodeToLogoUrl(tri: String?): String? {
    tri?.let { return "https://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_${tri.lowercase()}_light.png" }
        ?: run { return null }
}