package said.shatila.yinzcamexam.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateHelpers {
    const val API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun formatDateTime(
        date: String,
        inputPattern: String = API_FORMAT,
        outputPattern: String,
        locale: Locale = Locale.ENGLISH
    ): String {
        val inputFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern(outputPattern, locale)

        return runCatching {
            val parsedDate = LocalDateTime.parse(date, inputFormatter)
            parsedDate.format(outputFormatter)
        }.getOrElse { date }
    }
}