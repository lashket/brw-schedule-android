package by.railway.schedule.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.models.Ticket

@Database(entities = [Station::class, Ticket::class], version = AppDatabase.VERSION)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "station.db"
        const val VERSION = 2
    }

    abstract fun getStationsDao(): StationsDao

    abstract fun getTicketsDao(): TicketsDao

}