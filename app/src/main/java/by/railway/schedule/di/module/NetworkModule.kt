package by.railway.schedule.di.module

import by.railway.schedule.data.restful.ApiService
import by.railway.schedule.data.source.network.StationsNetworkRepository
import by.railway.schedule.data.source.network.StationsNetworkRepositoryImpl
import by.railway.schedule.di.scope.ApplicationScope
import by.railway.schedule.utils.BASE_RW_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory,
                        callAdapterFactory: CallAdapter.Factory,
                        client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_RW_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()

    @ApplicationScope
    @Provides
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @ApplicationScope
    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory.invoke()

    @ApplicationScope
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        builder.retryOnConnectionFailure(false)

        builder.addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("Connection", "close").build()
            chain.proceed(request)
        }

//        builder.addInterceptor(MyServiceInterceptor())
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)

        return builder.build()
    }

    @ApplicationScope
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @ApplicationScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @ApplicationScope
    @Provides
    fun provideStationsNetworkRepostiry(apiService: ApiService): StationsNetworkRepository {
        return StationsNetworkRepositoryImpl(apiService)
    }


//    @ApplicationScope
//    @Provides
//    fun provideRoomDatabase(application: Application): AppDatabase {
//        return Room
//            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
//            .fallbackToDestructiveMigration()
//            .build()
//    }

//    @ApplicationScope
//    @Provides
//    fun provideStationsDao(appDatabase: AppDatabase): StationsDao {
//        return appDatabase.getStationsDao()
//    }

}