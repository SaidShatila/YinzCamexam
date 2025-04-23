package said.shatila.yinzcamexam.data.local

data class Schedule(
    val team: Team,
    val defaultGameId: Long,
    val gameSection: List<GameSection>,
)
