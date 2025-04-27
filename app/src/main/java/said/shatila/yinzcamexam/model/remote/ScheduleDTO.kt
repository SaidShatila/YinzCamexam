package said.shatila.yinzcamexam.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDTO(
    @SerialName("DefaultGameId")
    val defaultGameId: Long? = null,
    @SerialName("Team")
    val team: TeamDTO? = null,
    @SerialName("GameSection")
    val gameSections: List<GameSectionDTO>? = null
)
