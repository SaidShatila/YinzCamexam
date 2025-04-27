package said.shatila.yinzcamexam.model.local

import androidx.compose.runtime.Immutable
import said.shatila.yinzcamexam.model.GameTypeData
import java.util.UUID

@Immutable
data class GameSection(
    val uniqueId: String = UUID.randomUUID().toString(),
    val heading: String,
    val games: List<GameTypeData>
)