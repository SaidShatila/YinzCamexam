package said.shatila.yinzcamexam.model

import said.shatila.yinzcamexam.model.local.Game

sealed class GameTypeData(val id: Any, val type: GameResult)

enum class GameResult(val gameType: String) {
    GAME("G"), // Game is for both Final and Scheduled
    FINAL("F"),
    SCHEDULED("S"),
    BYE("B")
}

data class GamesList(
    val idType: Any, val data: Game?
) : GameTypeData(idType, GameResult.GAME)

data class ByeList(val idType: Any, val data: Game?) : GameTypeData(idType, GameResult.BYE)