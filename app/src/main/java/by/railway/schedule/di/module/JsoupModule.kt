package by.railway.schedule.di.module

import by.railway.schedule.data.source.jsoup.JsoupRepository
import by.railway.schedule.data.source.jsoup.JsoupRepositoryImpl
import by.railway.schedule.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class JsoupModule {

    @ApplicationScope
    @Provides
    fun provideJsoupRepository(): JsoupRepository {
        return JsoupRepositoryImpl()
    }

}