package by.railway.schedule.domain.usecase

import by.railway.schedule.data.mapper.CloudErrorMapper
import by.railway.schedule.data.source.db.TicketsDao
import by.railway.schedule.domain.models.Ticket
import by.railway.schedule.domain.usecase.base.UseCase
import javax.inject.Inject

class GetAllTicketsFromDbUseCase @Inject constructor(
        errorMapper: CloudErrorMapper,
        private val ticketsDao: TicketsDao
): UseCase<MutableList<Ticket>>(errorMapper) {

    override suspend fun executeOnBackground(): MutableList<Ticket> {
        return ticketsDao.getAllTickets()
    }
}