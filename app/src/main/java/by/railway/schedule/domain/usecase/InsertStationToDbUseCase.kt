package by.railway.schedule.domain.usecase

import by.railway.schedule.data.mapper.CloudErrorMapper
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.usecase.base.UseCase
import javax.inject.Inject

@ApplicationScope
class InsertStationToDbUseCase @Inject constructor(
        errorUtil: CloudErrorMapper,
        private val stationsDao: StationsDao
): UseCase<Long>(errorUtil) {

    lateinit var station: ArrayList<Station>

    override suspend fun executeOnBackground(): Long {
        for (item in station) {
            stationsDao.insertStationToDb(item)
        }
        return 0L
    }
}