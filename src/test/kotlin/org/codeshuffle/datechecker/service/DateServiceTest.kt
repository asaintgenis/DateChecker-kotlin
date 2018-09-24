package org.codeshuffle.datechecker.service

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.Month
import java.time.format.DateTimeParseException

class DateServiceTest {
    private val dateService = DateService();

    @Test
    fun checkAndParseDate_OK() {
        val pattern = "yyyyMMdd"
        val dateToParse = "19870824"

        var date = dateService.checkAndParseDate(pattern, dateToParse)

        assertEquals(24, date.dayOfMonth)
        assertEquals(Month.AUGUST, date.month)
        assertEquals(1987, date.year)
    }

    @Test(expected = DateTimeParseException::class)
    fun checkAndParseDate_KO() {
        val pattern = "yyyyMMdd"
        val dateToParse = "24081987"


        dateService.checkAndParseDate(pattern, dateToParse)
    }

}