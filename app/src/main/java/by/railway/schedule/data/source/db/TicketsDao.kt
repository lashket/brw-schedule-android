package by.railway.schedule.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.railway.schedule.domain.models.Ticket
import retrofit2.http.DELETE

@Dao
interface TicketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicketToDb(ticket: Ticket): Long

    @Query("SELECT * from Ticket")
    suspend fun getAllTickets(): MutableList<Ticket>

    @Query("DELETE FROM Ticket WHERE id =:ticketId")
    fun deleteTicket(ticketId: Long)

}