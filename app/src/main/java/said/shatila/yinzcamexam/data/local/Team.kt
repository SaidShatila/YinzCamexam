package said.shatila.yinzcamexam.data.local

data class Team(
    val triCode: String,
    val fullName: String,
    val name: String,
    val city: String,
    val record: String,
    val wins: Int,
    val losses: Int,
    val winPercentage: Double,
    val primaryColor: String
)