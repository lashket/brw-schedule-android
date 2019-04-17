package by.railway.schedule.domain.repository

import by.railway.schedule.domain.models.Station

interface StationsRepository {
    suspend fun getAllStationsFromRemote(): ArrayList<Station>
}