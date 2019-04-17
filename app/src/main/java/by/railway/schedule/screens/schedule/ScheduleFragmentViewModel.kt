package by.railway.schedule.screens.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.railway.schedule.base.BaseViewModel
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.data.source.jsoup.JsoupRepository
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.models.response.ErrorModel
import by.railway.schedule.domain.usecase.DeleteAllStaitonsFromDbUseCase
import by.railway.schedule.domain.usecase.GetAllStationsFromNetworkUseCase
import by.railway.schedule.domain.usecase.GetStationsFromDbUseCase
import by.railway.schedule.domain.usecase.InsertStationToDbUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleFragmentViewModel @Inject constructor(
    private val getAllStationsFromNetworkUseCase: GetAllStationsFromNetworkUseCase,
    private val getStationsFromDbUseCase: GetStationsFromDbUseCase,
    private val insertStationToDbUseCase: InsertStationToDbUseCase,
    private val deleteAllStaitonsFromDbUseCase: DeleteAllStaitonsFromDbUseCase,
    private val jsoupRepository: JsoupRepository
): BaseViewModel() {

    val error : MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val departureStation: MutableLiveData<Station> by lazy { MutableLiveData<Station>() }
    val destinationStation: MutableLiveData<Station> by lazy { MutableLiveData<Station>() }
    val arriveTimeLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        getStationsFromDbUseCase.execute {
            onComplete { dbList ->

                    getAllStationsFromNetworkUseCase.execute {
                        onComplete {
                            networkList ->
                            if (dbList.size != networkList.size) {
                                insertStationToDbUseCase.station = networkList
                                insertStationToDbUseCase.execute {
                                    onComplete {

                                    }
                                }
                            }
                        }
                        onError {
                            error.value = it
                        }
                        onCancel {

                        }
                    }
            }
        }

    }

}