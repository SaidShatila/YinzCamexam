package said.shatila.yinzcamexam.data.remote

import kotlinx.serialization.SerialName

data class ScheduleDTO(
    @SerialName("DefaultGameId")
    val defaultGameId: String? = null,
    @SerialName("Team")
    val team: TeamDTO? = null,
    @SerialName("GameSection")
    val gameSections: List<GameSectionDTO>? = null
)
