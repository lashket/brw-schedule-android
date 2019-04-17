package by.railway.schedule.utils

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val TICKET_TIME_PARSED_PATTERN = "dd.MM.yyyy HH:mm"
    const val TICKET_TIME_PATTERN = "dd MMM HH:mm"
    private val DEFAULT_LOCALE = Locale("ru", "RU")

    fun getTicketTime(parsedTime: String): String {
        val format = SimpleDateFormat(TICKET_TIME_PARSED_PATTERN)
        try {

        } catch (e: ParseException) {

        }
        try {
            @SuppressLint("SimpleDateFormat") val simpleDateFormat = SimpleDateFormat(TICKET_TIME_PATTERN, DEFAULT_LOCALE)
            return simpleDateFormat.format(format.parse(parsedTime))
        } catch (e: Exception) {
            return ""
        }
    }

}