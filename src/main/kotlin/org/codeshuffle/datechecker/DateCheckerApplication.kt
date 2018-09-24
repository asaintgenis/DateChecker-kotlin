package org.codeshuffle.datechecker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DateCheckerApplication

fun main(args: Array<String>) {
    runApplication<DateCheckerApplication>(*args)
}
