package org.codeshuffle.datechecker.controller

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
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class DateController(val dateService: DateService, val legacyDateService: LegacyDateService, val dateMapper: DateMapper) {
    @PostMapping("/date")
    @Throws(ParseException::class)
    fun getDate(@Valid @RequestBody dateRequest: DateRequest): Resource<DateResponse> {
        val zonedDateTime = dateService.checkAndParseDate(dateRequest.pattern, dateRequest.dateToParse)
        val dateResponse = dateMapper.convertZoneDateTimeToDateResponse(zonedDateTime)
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