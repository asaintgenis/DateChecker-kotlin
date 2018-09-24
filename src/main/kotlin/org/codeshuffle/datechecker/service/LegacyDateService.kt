package org.codeshuffle.datechecker.service

import org.springframework.stereotype.Service
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Service
class LegacyDateService {

    @Throws(ParseException::class)
    fun checkAndParseDate(pattern: String, dateToParse: String): Calendar {
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.parse(dateToParse)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }
}