package org.codeshuffle.datechecker.service

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

@Service
class DateService {

    fun checkAndParseLocalDate(pattern: String, dateToParse: String): LocalDate {
        val dateTimeFormatterBuilder = DateTimeFormatterBuilder()
        val dateTimeFormatter = dateTimeFormatterBuilder.append(DateTimeFormatter.ofPattern(pattern)).toFormatter()
        return LocalDate.parse(dateToParse, dateTimeFormatter)
    }

    fun checkAndParseZonedDateTime(pattern: String, dateToParse: String): ZonedDateTime {
        val dateTimeFormatterBuilder = DateTimeFormatterBuilder()
        val dateTimeFormatter = dateTimeFormatterBuilder.append(DateTimeFormatter.ofPattern(pattern)).toFormatter()
        return ZonedDateTime.parse(dateToParse, dateTimeFormatter)
    }

    fun checkAndParseLocalDateTime(pattern: String, dateToParse: String): LocalDateTime {
        val dateTimeFormatterBuilder = DateTimeFormatterBuilder()
        val dateTimeFormatter = dateTimeFormatterBuilder.append(DateTimeFormatter.ofPattern(pattern)).toFormatter()
        return LocalDateTime.parse(dateToParse, dateTimeFormatter)
    }
}