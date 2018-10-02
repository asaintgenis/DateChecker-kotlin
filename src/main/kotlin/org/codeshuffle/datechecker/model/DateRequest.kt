package org.codeshuffle.datechecker.model

import org.codeshuffle.datechecker.constant.DateType

data class DateRequest(val pattern: String, val dateToParse: String, val dateType: DateType)