package said.shatila.yinzcamexam.model

import said.shatila.yinzcamexam.data.local.Game

sealed class GameTypeData(val id: Any, val type: GameResult)

enum class GameResult(val gameType: String) {
    FINAL("F"),
    SCHEDULED("S"),
    BYE("B")
}

data class FinalList(
    val idType: Any, val data: Game?
) : GameTypeData(idType, GameResult.FINAL)

data class ByeList(val idType: Any, val data: Game?) : GameTypeData(idType, GameResult.BYE)

data class ScheduledList(val idType: Any, val data: Game?) :
    GameTypeData(idType, GameResult.SCHEDULED)
