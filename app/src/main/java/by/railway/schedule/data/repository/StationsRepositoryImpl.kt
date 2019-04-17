package by.railway.schedule.data.repository

import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.domain.models.Station
import by.railway.schedule.data.source.network.StationsNetworkRepository
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.repository.StationsRepository
import javax.inject.Inject

class StationsRepositoryImpl @Inject constructor(
    private val stationsNetworkRepository: StationsNetworkRepository
): StationsRepository {

    override suspend fun getAllStationsFromRemote(): ArrayList<Station> {
        return stationsNetworkRepository.getStations()
    }
}