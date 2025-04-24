package said.shatila.yinzcamexam.data.local

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class GameSection(
    val uniqueId: String = UUID.randomUUID().toString(),
    val heading: String,
    val games: List<Game>
)