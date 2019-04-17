package by.railway.schedule.di.component

import by.railway.schedule.App
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.di.module.ApplicationModule
import by.railway.schedule.di.module.DBModule
import by.railway.schedule.di.module.JsoupModule
import by.railway.schedule.di.module.NetworkModule
import by.railway.schedule.screens.board.OnlineBoardFragment
import by.railway.schedule.screens.main.MainActivity
import by.railway.schedule.screens.schedule.ScheduleFragment
import by.railway.schedule.screens.schedule.results.SearchResultsFragment
import by.railway.schedule.screens.schedule.station.SearchStationActivity
import by.railway.schedule.screens.tickets.TicketsFragment
import dagger.Component

@ApplicationScope
@Component(modules = [
    ApplicationModule::class,
    DBModule::class,
    NetworkModule::class,
    JsoupModule::class
])
interface ApplicationComponent {

    fun inject(itvLifeApp: App)

    fun inject(scheduleFragment: ScheduleFragment)

    fun inject(searchStationActivity: SearchStationActivity)

    fun inject(onlineBoardFragment: OnlineBoardFragment)

    fun inject(searchResultsFragment: SearchResultsFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(ticketsFragment: TicketsFragment)

}