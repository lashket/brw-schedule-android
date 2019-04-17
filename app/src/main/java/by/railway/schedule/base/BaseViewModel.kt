package by.railway.schedule.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.railway.schedule.domain.models.response.ErrorModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel() {

    enum class Status {
        COMPLETE, ERROR
    }

    val parentJob = Job()

    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val scope = CoroutineScope(coroutineContext)

    val statusLiveData: MutableLiveData<Status> by lazy { MutableLiveData<Status>() }

    val errorModelLiveData: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

}