package by.railway.schedule.di.module

import android.content.Context
import by.railway.schedule.App
import by.railway.schedule.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: App) {

    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context = app.applicationContext

}