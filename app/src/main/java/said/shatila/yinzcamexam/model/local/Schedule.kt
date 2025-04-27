package said.shatila.yinzcamexam.model.local

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Schedule(
    val uniqueId: String = UUID.randomUUID().toString(),
    val team: Team? = null,
    val defaultGameId: Long? = null,
    val gameSection: List<GameSection>? = null,
)
