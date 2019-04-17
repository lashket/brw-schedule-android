package by.railway.schedule.utils

import by.railway.schedule.domain.models.Ticket

object Helper {

    fun getTicket(incomeText: String): Ticket {
        val text = incomeText.replace("\n", "")
        val trainNumber = text.substring(text.indexOf("на поезд") + 8, text.indexOf("по маршруту")).trim()
        val trainRoute = text.substring(text.indexOf("по маршруту") + 11, text.indexOf("отправлением")).trim()
        val departureTime = text.substring(text.indexOf("отправлением") + 12, text.indexOf("(в")).trim()
        var wagonNumber =text.substring(text.indexOf("вагон:") + 6, text.indexOf(", места")).trim()
        val seats = text.substring(text.indexOf("места") + 6, text.indexOf(") вы")).trim()
        wagonNumber = wagonNumber.replace("N8", "")
        wagonNumber = wagonNumber.replace("№", "")
        return Ticket(System.currentTimeMillis(), trainNumber, seats, trainRoute, wagonNumber, departureTime)
    }

}