package by.railway.schedule.screens.board

import androidx.lifecycle.MutableLiveData
import by.railway.schedule.base.BaseViewModel
import by.railway.schedule.data.source.jsoup.JsoupRepository
import by.railway.schedule.domain.models.Train
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnlineBoardFragmentViewModel @Inject constructor(
    private val jsoupRepository: JsoupRepository
): BaseViewModel() {

    val models: MutableLiveData<ArrayList<Train>> by lazy { MutableLiveData<ArrayList<Train>>() }

    init {
        loadSchedule()
    }

    private fun loadSchedule() {
        scope.launch {
            models.postValue(jsoupRepository.getOnlineTableItems())
        }
    }

}