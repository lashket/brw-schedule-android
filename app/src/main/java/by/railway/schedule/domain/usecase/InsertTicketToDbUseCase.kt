package by.railway.schedule.domain.usecase

import by.railway.schedule.data.mapper.CloudErrorMapper
import by.railway.schedule.data.source.db.TicketsDao
import by.railway.schedule.domain.models.Ticket
import by.railway.schedule.domain.usecase.base.UseCase
import javax.inject.Inject

class InsertTicketToDbUseCase @Inject constructor(
        errorMapper: CloudErrorMapper,
        private val ticketsDao: TicketsDao
): UseCase<Long>(errorMapper) {

    lateinit var ticket: Ticket

    override suspend fun executeOnBackground(): Long {
        ticketsDao.insertTicketToDb(ticket)
        return 0L
    }
}