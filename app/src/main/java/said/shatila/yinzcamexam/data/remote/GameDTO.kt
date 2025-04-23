package said.shatila.yinzcamexam.data.remote

import kotlinx.serialization.SerialName

data class GameDTO(
    @SerialName("Week")
    val week: String? = null,
    @SerialName("Label")
    val label: String? = null,
    @SerialName("TV")
    val tv: String? = null,
    @SerialName("Radio")
    val radio: String? = null,
    @SerialName("WLT")
    val wlt: String? = null,
    @SerialName("GameState")
    val gameState: String? = null,
    @SerialName("AwayScore")
    val awayScore: String? = null,
    @SerialName("HomeScore")
    val homeScore: String? = null,
    @SerialName("Id")
    val id: Long? = null,
    @SerialName("Type")
    val type: String? = null,
    @SerialName("Date")
    val date: GameDateDTO? = null,
    @SerialName("Opponent")
    val opponent: TeamDTO? = null,
)
