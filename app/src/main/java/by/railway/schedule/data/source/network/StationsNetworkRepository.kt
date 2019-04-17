package by.railway.schedule.data.source.network

import by.railway.schedule.domain.models.Station

interface StationsNetworkRepository {
    suspend fun getStations(): ArrayList<Station>

}