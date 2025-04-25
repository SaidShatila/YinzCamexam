package said.shatila.yinzcamexam.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameSectionDTO(
    @SerialName("Heading")
    val heading: String? = null,
    @SerialName("Game")
    val games: List<GameDTO>? = null
)
