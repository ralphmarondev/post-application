package com.maronworks.postapplication.mainf.domain.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getCurrentDateTime(): String{
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' h:mma", Locale.ENGLISH)

    return currentDateTime.format(formatter)
}