package said.shatila.yinzcamexam.extension

import said.shatila.yinzcamexam.data.local.Game
import said.shatila.yinzcamexam.data.remote.GameDTO
import said.shatila.yinzcamexam.data.remote.ScheduleDTO
import said.shatila.yinzcamexam.data.remote.TeamDTO
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor


fun ScheduleDTO.toSectionUIModels(): List<GameSectionUIModel> =
    gameSections.map { section ->
        GameSectionUIModel(
            heading = section.heading,
            games = section.games.map { dto -> dto.toGame(team) }
        )
    }

fun GameDTO.toGame(homeTeam: TeamDTO): Game {

    return Game(
        id = id ?: 0,
        week = week.orEmpty(),
        label = label.orEmpty(),
        gameStateDate = "$state $clock",
        tvRadio = (tv?.takeIf { it.isNotEmpty() } ?: radio)?.takeIf { it.isNotEmpty() },
        isRecord = type == "F",
        awayRecordScore = awayScore.orEmpty(),
        homeRecordScore = homeScore.orEmpty(),
        homeAwayRecordColor = PrimaryTextColor,
        dateText = date?.text.orEmpty(),
        homeTeamName = homeTeam.fullName.orEmpty(),
        homeTeamLogo = triCodeToLogoUrl(homeTeam.triCode.orEmpty()),
        opponentTeamName = opponent?.name.orEmpty(),
        opponentTeamLogo = triCodeToLogoUrl(opponent?.triCode.orEmpty())
    )
}

private fun triCodeToLogoUrl(tri: String): String {
    return "https://static.nfl.com/team-logos/$tri.png"
}