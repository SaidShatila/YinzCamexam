package said.shatila.yinzcamexam.data.local

import androidx.compose.runtime.Immutable

@Immutable
data class Team(
    val name: String,
    val record: String,
    val teamLogoUrl: String,
)