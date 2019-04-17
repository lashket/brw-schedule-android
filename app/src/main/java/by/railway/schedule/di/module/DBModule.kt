package by.railway.schedule.di.module

import android.app.Application
import androidx.room.Room
import by.railway.schedule.App
import by.railway.schedule.data.source.db.AppDatabase
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.data.source.db.TicketsDao
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.domain.repository.StationsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DBModule @Inject constructor(
    app: Application
){

    var database: AppDatabase = Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @ApplicationScope
    @Provides
    fun provideDatabase(): AppDatabase {
        return database
    }

    @ApplicationScope
    @Provides
    fun provideStationsDao(database: AppDatabase): StationsDao {
        return database.getStationsDao()
    }

    @ApplicationScope
    @Provides
    fun provideTicketsDao(database: AppDatabase): TicketsDao = database.getTicketsDao()

}