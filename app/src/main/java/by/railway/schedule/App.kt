package by.railway.schedule


import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import by.railway.schedule.di.component.ApplicationComponent
import by.railway.schedule.di.component.DaggerApplicationComponent
import by.railway.schedule.di.module.ApplicationModule
import by.railway.schedule.di.module.DBModule

class App : Application() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dBModule(DBModule(this))
                .build()
    }

}