package by.railway.schedule.screens.tickets

import androidx.lifecycle.MutableLiveData
import by.railway.schedule.base.BaseViewModel
import by.railway.schedule.domain.models.Ticket
import by.railway.schedule.domain.usecase.GetAllTicketsFromDbUseCase
import javax.inject.Inject

class TicketsFragmentViewModel @Inject constructor(
        getAllTicketsFromDbUseCase: GetAllTicketsFromDbUseCase
): BaseViewModel() {

    val ticketsLiveData: MutableLiveData<ArrayList<Ticket>> by lazy { MutableLiveData<ArrayList<Ticket>>() }

    init {
        getAllTicketsFromDbUseCase.execute {
            onComplete {
                ticketsLiveData.postValue(it as ArrayList<Ticket>)
            }
            onError {
                errorModelLiveData.postValue(it)
            }
        }
    }

}