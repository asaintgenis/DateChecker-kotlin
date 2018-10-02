package org.codeshuffle.datechecker.mapper

import org.codeshuffle.datechecker.model.DateResponse
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.*
import java.util.Calendar



@Component
class DateMapper {
    fun convertZoneDateTimeToDateResponse(zonedDateTime: ZonedDateTime) : DateResponse {
        return DateResponse(zonedDateTime.year.toString(),
                zonedDateTime.month.toString(),
                zonedDateTime.dayOfMonth.toString(),
                zonedDateTime.hour.toString(),
                zonedDateTime.minute.toString(),
                zonedDateTime.second.toString(),
                zonedDateTime.nano.toString(),
                zonedDateTime.zone.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH))
    }

    fun convertLocalDateTimeToDateResponse(localDateTime: LocalDateTime) : DateResponse {
        return DateResponse(localDateTime.year.toString(),
                localDateTime.month.toString(),
                localDateTime.dayOfMonth.toString(),
                localDateTime.hour.toString(),
                localDateTime.minute.toString(),
                localDateTime.second.toString(),
                localDateTime.nano.toString())
    }

    fun convertLocalDateToDateResponse(localDate: LocalDate) : DateResponse {
        return DateResponse(localDate.year.toString(),
                localDate.month.toString(),
                localDate.dayOfMonth.toString())
    }

    fun convertDateToDateResponse(calendar: Calendar) : DateResponse {
        return DateResponse(calendar.get(Calendar.YEAR).toString(),
                calendar.get(Calendar.MONTH).toString(),
                calendar.get(Calendar.DAY_OF_MONTH).toString(),
                calendar.get(Calendar.HOUR_OF_DAY).toString(),
                calendar.get(Calendar.MINUTE).toString(),
                calendar.get(Calendar.SECOND).toString(),
                calendar.get(Calendar.MILLISECOND).toString(),
                calendar.timeZone.displayName)
    }
}