package org.codeshuffle.datechecker.mapper

import org.codeshuffle.datechecker.model.DateResponse
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.*
import java.util.GregorianCalendar
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
                zonedDateTime.zone.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.ENGLISH))
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