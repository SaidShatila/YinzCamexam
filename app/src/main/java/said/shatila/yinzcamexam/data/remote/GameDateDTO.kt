package said.shatila.yinzcamexam.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDateDTO(
    @SerialName("Numeric")
    val numeric: String? = null,
    @SerialName("Text")
    val text: String? = null,
    @SerialName("Time")
    val time: String? = null,
    @SerialName("Timestamp")
    val timestamp: String? = null,
    @SerialName("IsTBA")
    val isTBA: String? = null,
)
