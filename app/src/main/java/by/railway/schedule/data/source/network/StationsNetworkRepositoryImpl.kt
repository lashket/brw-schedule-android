package by.railway.schedule.data.source.network

import by.railway.schedule.domain.models.Station
import by.railway.schedule.data.restful.ApiService
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class StationsNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): StationsNetworkRepository {

    override suspend fun getStations(): ArrayList<Station> {
        return apiService.getStations().await()
    }


}