package said.shatila.yinzcamexam.data.local

data class Game(
    val id: Long,
    val week: String,
    val label: String,
    val gameState: String,
    val tv: String,
    val radio: String,
    val gameOutcome: GameResult,
    val awayScore: String,
    val homeScore: String,
    val gameType: GameType,
    val gameDate: GameDate,
    val opponentTeam: Team
)