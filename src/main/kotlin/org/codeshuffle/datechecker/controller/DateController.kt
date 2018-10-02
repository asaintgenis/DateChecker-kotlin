package org.codeshuffle.datechecker.controller

import org.codeshuffle.datechecker.constant.DateType
import org.codeshuffle.datechecker.exception.LegacyTypeException
import org.codeshuffle.datechecker.mapper.DateMapper
import org.codeshuffle.datechecker.model.DateRequest
import org.codeshuffle.datechecker.model.DateResponse
import org.codeshuffle.datechecker.service.DateService
import org.codeshuffle.datechecker.service.LegacyDateService
import org.springframework.hateoas.Resource
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.ParseException
import javax.validation.Valid



@RestController
@RequestMapping("/api")
class DateController(val dateService: DateService, val legacyDateService: LegacyDateService, val dateMapper: DateMapper) {
    @PostMapping("/date")
    @Throws(ParseException::class)
    fun getDate(@Valid @RequestBody dateRequest: DateRequest): Resource<DateResponse> {
        val dateResponse: DateResponse
        when(dateRequest.dateType) {
            DateType.LOCAL_DATE -> {
                dateResponse = dateMapper.convertLocalDateToDateResponse(dateService.checkAndParseLocalDate(dateRequest.pattern, dateRequest.dateToParse))
            }
            DateType.LOCAL_DATETIME -> {
                dateResponse = dateMapper.convertLocalDateTimeToDateResponse(dateService.checkAndParseLocalDateTime(dateRequest.pattern, dateRequest.dateToParse))

            }
            DateType.ZONED_DATETIME -> {
                dateResponse = dateMapper.convertZoneDateTimeToDateResponse(dateService.checkAndParseZonedDateTime(dateRequest.pattern, dateRequest.dateToParse))

            }
            DateType.LEGACY -> {
                throw LegacyTypeException("DateType LEGACY cannot be use to call /date endpoint")
            }
        }
        val resource = Resource<DateResponse>(dateResponse)
        val linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.javaClass).getLegacyDate(dateRequest))
        resource.add(linkTo.withRel("legacy-date"))

        return resource
    }

    @PostMapping("/legacyDate")
    @Throws(ParseException::class)
    fun getLegacyDate(@Valid @RequestBody dateRequest: DateRequest): Resource<DateResponse> {
        val date = legacyDateService.checkAndParseDate(dateRequest.pattern, dateRequest.dateToParse)
        val dateResponse = dateMapper.convertDateToDateResponse(date)

        val resource = Resource<DateResponse>(dateResponse)
        val linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.javaClass).getDate(dateRequest))
        resource.add(linkTo.withRel("java8-date"))

        return resource
    }
}