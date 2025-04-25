package said.shatila.yinzcamexam.extension

import androidx.compose.ui.text.font.FontWeight
import said.shatila.yinzcamexam.data.local.Game
import said.shatila.yinzcamexam.data.local.GameSection
import said.shatila.yinzcamexam.data.remote.GameDTO
import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.data.remote.TeamDTO
import said.shatila.yinzcamexam.model.ByeList
import said.shatila.yinzcamexam.model.FinalList
import said.shatila.yinzcamexam.model.GameResult
import said.shatila.yinzcamexam.model.ScheduledList
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor
import said.shatila.yinzcamexam.ui.theme.SecondaryTextColor
import said.shatila.yinzcamexam.utils.DateHelpers
import java.util.UUID

fun ScheduleDTO.toSectionUIModels(): List<GameSection> {
    val sections = gameSections ?: return emptyList()
    return sections.map { section ->
        GameSection(
            heading = section.heading.orEmpty(),
            games = section.games?.map { dto ->
                val game = team?.let { dto.toGame(it, dto.type.equals(GameResult.FINAL.name)) }

                when (dto.type) {
                    GameResult.FINAL.name ->
                        FinalList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = game
                        )

                    GameResult.BYE.name ->
                        ByeList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = null
                        )

                    GameResult.SCHEDULED.name ->
                        ScheduledList(
                            idType = game?.uniqueId ?: UUID.randomUUID(),
                            data = game
                        )

                    else ->
                        ScheduledList(
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
        awayRecordScore = if (isFinal) awayScore.orEmpty() else opponent?.record.orEmpty(),
        homeRecordScore = if (isFinal) homeScore.orEmpty() else homeTeam.record.orEmpty(),
        homeAwayRecordColor = if (isFinal) PrimaryTextColor else SecondaryTextColor,
        homeAwayRecordFontWeight = if (isFinal) FontWeight.Bold else FontWeight.Normal,
        dateText = DateHelpers.formatDateTime(date?.text.orEmpty(), outputPattern = "EEE, MMM dd"),
        homeTeamName = homeTeam.fullName.orEmpty(),
        homeTeamLogo = triCodeToLogoUrl(homeTeam.triCode.orEmpty()),
        opponentTeamName = opponent?.name.orEmpty(),
        opponentTeamLogo = triCodeToLogoUrl(opponent?.triCode.orEmpty())
    )
}

private fun triCodeToLogoUrl(tri: String): String {
    return "https://static.nfl.com/team-logos/$tri.png"
}