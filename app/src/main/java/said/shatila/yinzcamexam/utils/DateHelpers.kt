package said.shatila.yinzcamexam.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateHelpers {
    fun formatDateTime(
        isoTimestamp: String,
        outputPattern: String,
        locale: Locale = Locale.getDefault()
    ): String = runCatching {
        val instant = Instant.parse(isoTimestamp)
        val zoned = instant.atZone(ZoneId.systemDefault())
        DateTimeFormatter.ofPattern(outputPattern, locale)
            .format(zoned)
    }.getOrDefault(isoTimestamp)
}