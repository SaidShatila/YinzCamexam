package said.shatila.yinzcamexam.model.local

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import said.shatila.yinzcamexam.ui.theme.PrimaryTextColor
import java.util.UUID

@Immutable
data class Game(
    val uniqueId: String = UUID.randomUUID().toString(),
    val id: Long,
    val week: String,
    val label: String,
    val gameStateDate: String,
    val tvRadio: String? = null,
    val awayRecordScore: String,
    val homeRecordScore: String,
    val isRecord : Boolean = false,
    val homeAwayRecordColor: Color = PrimaryTextColor,
    val homeAwayRecordFontWeight: FontWeight = FontWeight.Normal,
    val dateText: String,
    val homeTeamName: String,
    val homeTeamLogo: Any? = null,
    val opponentTeamName: String,
    val opponentTeamLogo: Any? = null,
)