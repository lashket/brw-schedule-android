package by.railway.schedule.domain.usecase

import by.railway.schedule.data.mapper.CloudErrorMapper
import by.railway.schedule.domain.models.Station
import by.railway.schedule.data.source.network.StationsNetworkRepository
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.usecase.base.UseCase
import javax.inject.Inject

@ApplicationScope
class GetAllStationsFromNetworkUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val stationsRepository: StationsNetworkRepository
): UseCase<ArrayList<Station>>(errorUtil) {
    override suspend fun executeOnBackground(): ArrayList<Station> {
        return stationsRepository.getStations()
    }
}