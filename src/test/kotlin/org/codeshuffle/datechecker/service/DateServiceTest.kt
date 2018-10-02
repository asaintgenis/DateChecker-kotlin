package org.codeshuffle.datechecker.service

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.Month
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.*

class DateServiceTest {
    private val dateService = DateService();

    @Test
    fun checkAndParseLocalDate_OK() {
        val pattern = "yyyy-MM-dd"
        val dateToParse = "1987-08-24"

        var date = dateService.checkAndParseLocalDate(pattern, dateToParse)

        assertEquals(24, date.dayOfMonth)
        assertEquals(Month.AUGUST, date.month)
        assertEquals(1987, date.year)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseLocalDate_KO_invalidYear() {
        val pattern = "yyyy-MM-dd"
        val dateToParse = "2408-19-87"


        dateService.checkAndParseLocalDate(pattern, dateToParse)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseLocalDate_KO_noTime_noZone() {
        val pattern = "yyyy-MM-dd"
        val dateToParse = "1987-08-24"


        dateService.checkAndParseZonedDateTime(pattern, dateToParse)
    }

    @Test
    fun checkAndParseLocalDateTime_OK() {
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val dateToParse = "1987-08-24 08:20:40"
        var date = dateService.checkAndParseLocalDateTime(pattern, dateToParse)

        assertEquals(24, date.dayOfMonth)
        assertEquals(Month.AUGUST, date.month)
        assertEquals(1987, date.year)
        assertEquals(8, date.hour)
        assertEquals(20, date.minute)
        assertEquals(40, date.second)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseLocalDateTime_KO_invalidHour() {
        val pattern = "yyyy-MM-dd hh:mm:ss"
        val dateToParse = "1987-08-24 25:20:40"


        dateService.checkAndParseLocalDateTime(pattern, dateToParse)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseLocalDateTime_KO_noTime() {
        val pattern = "yyyy-MM-dd"
        val dateToParse = "1987-08-24"


        dateService.checkAndParseLocalDateTime(pattern, dateToParse)
    }

    @Test
    fun checkAndParseZonedDateTime_OK() {
        val pattern = "yyyy-MM-dd HH:mm:ss VV"
        val dateToParse = "1987-08-24 08:20:40 America/Los_Angeles"
        var date = dateService.checkAndParseZonedDateTime(pattern, dateToParse)

        assertEquals(24, date.dayOfMonth)
        assertEquals(Month.AUGUST, date.month)
        assertEquals(1987, date.year)
        assertEquals(8, date.hour)
        assertEquals(20, date.minute)
        assertEquals(40, date.second)
        assertEquals("Pacific Time", date.zone.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH))
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseZonedDateTime_KO_invalidZone() {
        val pattern = "yyyy-MM-dd hh:mm:ss VV"
        val dateToParse = "1987-08-24 25:20:40 Foo/Foo"


        dateService.checkAndParseZonedDateTime(pattern, dateToParse)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseZonedDateTime_KO_noTime() {
        val pattern = "yyyy-MM-dd HH:mm:ss VV"
        val dateToParse = "1987-08-24 America/Los_Angeles"


        dateService.checkAndParseZonedDateTime(pattern, dateToParse)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseZonedDateTime_KO_noTime_noZone() {
        val pattern = "yyyy-MM-dd HH:mm:ss VV"
        val dateToParse = "1987-08-24"


        dateService.checkAndParseZonedDateTime(pattern, dateToParse)
    }

}