package by.railway.schedule.screens.schedule.results

import androidx.lifecycle.MutableLiveData
import by.railway.schedule.base.BaseViewModel
import by.railway.schedule.data.source.jsoup.JsoupRepository
import by.railway.schedule.domain.models.Station
import by.railway.schedule.domain.models.Train
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultFragmentViewModel @Inject constructor(
        private val jsoupRepository: JsoupRepository
): BaseViewModel() {

    val trainsLiveData: MutableLiveData<ArrayList<Train>> by lazy { MutableLiveData<ArrayList<Train>>() }


    fun getTrains(departureTime: String,
                  arriveStation: Station,
                  destinationStation: Station) {
        scope.launch {
            trainsLiveData.postValue(jsoupRepository.getTripTrainsList(departureTime, arriveStation, destinationStation))
        }
    }

}