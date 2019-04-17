package by.railway.schedule.screens.schedule.station

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.railway.schedule.data.source.db.StationsDao
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.models.response.ErrorModel
import by.railway.schedule.domain.usecase.GetAllStationsFromNetworkUseCase
import by.railway.schedule.domain.usecase.GetStationsFromDbUseCase
import javax.inject.Inject

class SearchStationActivityViewModel @Inject constructor(
    private val getAllStationsFromNetworkUseCase: GetAllStationsFromNetworkUseCase,
    private val getStationsFromDbUseCase: GetStationsFromDbUseCase
): ViewModel() {

    val homeData: MutableLiveData<ArrayList<Station>> by lazy { MutableLiveData<ArrayList<Station>>() }
    val error : MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }


    init {
        getStationsFromDbUseCase.execute {
            onComplete {
                if (it.size == 0) {
                    getAllStationsFromNetworkUseCase.execute {
                        onComplete {
                            homeData.value = it
                        }
                        onError {
                            error.value = it
                        }
                        onCancel {

                        }
                    }
                } else {
                    homeData.value = it as ArrayList<Station>
                }
            }
        }

    }

}