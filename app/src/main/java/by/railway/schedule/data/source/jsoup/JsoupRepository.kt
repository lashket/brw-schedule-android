package by.railway.schedule.data.source.jsoup

import by.railway.schedule.domain.models.Train
import by.railway.schedule.domain.models.Station

interface JsoupRepository {

    suspend fun getOnlineTableItems(): ArrayList<Train>

    suspend fun getTripTrainsList(arriveDate: String, arriveStation: Station, destinationStation: Station): ArrayList<Train>

}