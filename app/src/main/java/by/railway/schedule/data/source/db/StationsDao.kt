package by.railway.schedule.data.source.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.models.Station

@Dao
interface StationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStationToDb(station: Station): Long

    @Query("SELECT * from Station")
    suspend fun getAllStations(): MutableList<Station>

}