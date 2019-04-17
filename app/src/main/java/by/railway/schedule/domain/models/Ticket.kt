package by.railway.schedule.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ticket(

        @PrimaryKey
        val id: Long,
        val trainNumber: String,
        val seats: String,
        val trainRoute: String,
        val wagonNumber: String,
        val departureTime: String
)