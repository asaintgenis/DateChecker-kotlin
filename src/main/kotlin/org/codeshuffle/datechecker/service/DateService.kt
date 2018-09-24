package org.codeshuffle.datechecker.service

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class DateService {
    fun checkAndParseDate(pattern: String, dateToParse: String): LocalDate {
        return LocalDate.parse(dateToParse, DateTimeFormatter.ofPattern(pattern))
    }
}