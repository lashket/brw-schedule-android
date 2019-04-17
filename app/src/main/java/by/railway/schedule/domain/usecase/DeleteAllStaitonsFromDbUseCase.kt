package by.railway.schedule.domain.usecase

import by.railway.schedule.data.mapper.CloudErrorMapper
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.usecase.base.UseCase
import javax.inject.Inject

@ApplicationScope
class DeleteAllStaitonsFromDbUseCase @Inject constructor(
        errorUtil: CloudErrorMapper,
        private val stationsDao: StationsDao
): UseCase<Int>(errorUtil) {

    override suspend fun executeOnBackground(): Int {
//        stationsDao.deleteAllFromStation()
        return 1
    }
}