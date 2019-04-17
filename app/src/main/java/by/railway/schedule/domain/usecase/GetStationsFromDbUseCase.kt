package by.railway.schedule.domain.usecase

import by.railway.schedule.data.mapper.CloudErrorMapper
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.repository.StationsRepository
import by.railway.schedule.domain.usecase.base.UseCase
import javax.inject.Inject

@ApplicationScope
class GetStationsFromDbUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val stationsDao: StationsDao
): UseCase<MutableList<Station>>(errorUtil) {

    override suspend fun executeOnBackground(): MutableList<Station> {
        return stationsDao.getAllStations()
    }
}