package by.railway.schedule.data.restful

import by.railway.schedule.domain.models.Station
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("ajax/autocomplete/search/?term=")
    fun getStations(): Deferred<ArrayList<Station>>

}