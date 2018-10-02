package org.codeshuffle.datechecker.service

import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class DateService {
    fun checkAndParseDate(pattern: String, dateToParse: String): ZonedDateTime {
        return ZonedDateTime.parse(dateToParse, DateTimeFormatter.ofPattern(pattern))
    }
}