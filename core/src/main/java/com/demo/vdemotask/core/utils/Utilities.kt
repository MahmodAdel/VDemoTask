package com.demo.vdemotask.core.utils

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object Utilities {

    fun getDateTime():String{
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
    }
}