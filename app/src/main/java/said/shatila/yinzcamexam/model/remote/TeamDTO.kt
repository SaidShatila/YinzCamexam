package said.shatila.yinzcamexam.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDTO(
    @SerialName("TriCode")
    val triCode: String? = null,
    @SerialName("FullName")
    val fullName: String? = null,
    @SerialName("Name")
    val name: String? = null,
    @SerialName("City")
    val city: String? = null,
    @SerialName("Record")
    val record: String? = null,
    @SerialName("Wins")
    val wins: Int? = null,
    @SerialName("Losses")
    val losses: Int? = null,
    @SerialName("WinPercentage")
    val winPercentage: Double? = null,
    @SerialName("PrimaryColor")
    val primaryColor: String? = null
)
