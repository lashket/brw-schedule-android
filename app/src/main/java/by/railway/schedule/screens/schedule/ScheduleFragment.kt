package by.railway.schedule.screens.schedule

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import by.railway.schedule.R
import by.railway.schedule.base.BaseFragment
import by.railway.schedule.base.ViewModelFactory
import by.railway.schedule.domain.models.Station
import by.railway.schedule.screens.schedule.station.SearchStationActivity
import by.railway.schedule.utils.ARG_OBJECT
import by.railway.schedule.utils.RQ_PICK_DEPARTURE_STATION
import by.railway.schedule.utils.RQ_PICK_DESTINATION_STATION
import kotlinx.android.synthetic.main.fragment_schedule.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ScheduleFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ScheduleFragmentViewModel>
    lateinit var viewModel: ScheduleFragmentViewModel

    private var dateAndTime = Calendar.getInstance()
    private val dateFormat = "yyyy-MM-dd"

    var d: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        dateAndTime.set(Calendar.YEAR, year)
        dateAndTime.set(Calendar.MONTH, monthOfYear)
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val df = SimpleDateFormat(dateFormat)
        viewModel.arriveTimeLiveData.value = df.format(dateAndTime.time)
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_schedule

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ScheduleFragmentViewModel::class.java)
        with(viewModel) {
            departureStation.observe(this@ScheduleFragment, androidx.lifecycle.Observer {
                til_departure_station.editText?.setText(it.label)
            })
            destinationStation.observe(this@ScheduleFragment, androidx.lifecycle.Observer {
                til_destination_station.editText?.setText(it.label)
            })
            arriveTimeLiveData.observe(this@ScheduleFragment, androidx.lifecycle.Observer {
                til_departure_date.editText?.setText(it)
            })
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_search.setOnClickListener {
            if (!isDataCorrect()) {
                return@setOnClickListener
            }
            val action = ScheduleFragmentDirections.actionScheduleFragmentToSearchResultsFragment(
                    viewModel.departureStation.value!!,
                    viewModel.destinationStation.value!!,
                    viewModel.arriveTimeLiveData.value!!
            )
            Navigation.findNavController(getView()!!).navigate(action)
        }
        select_date_view.setOnClickListener { showDatePickDialog() }
        select_departure_station_view.setOnClickListener { SearchStationActivity.showForResult(this, RQ_PICK_DEPARTURE_STATION) }
        select_destination_station_view.setOnClickListener { SearchStationActivity.showForResult(this, RQ_PICK_DESTINATION_STATION) }
        btn_change.setOnClickListener { changeItems() }
    }

    private fun showDatePickDialog() {
        DatePickerDialog(activity, R.style.DialogTheme, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show()
    }

    private fun changeItems() {
        val temp = viewModel.departureStation.value
        viewModel.departureStation.value = viewModel.destinationStation.value
        viewModel.destinationStation.value = temp
    }

    private fun isDataCorrect(): Boolean {
        var isDataCorrect = true
        with(viewModel) {
            til_departure_date.isErrorEnabled = false
            til_departure_station.isErrorEnabled = false
            til_destination_station.isErrorEnabled = false
            if (departureStation.value == null) {
                isDataCorrect = false
                til_departure_station.error = "Выберите станцию отправления"
            }
            if (destinationStation.value == null) {
                isDataCorrect = false
                til_destination_station.error = "Выберите станцию прибытия"
            }
            if (arriveTimeLiveData.value.isNullOrEmpty()) {
                isDataCorrect = false
                til_departure_date.error = "Выберите дату отправления"

            }
        }
        return isDataCorrect
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RQ_PICK_DESTINATION_STATION -> viewModel.destinationStation.value = data?.getSerializableExtra(ARG_OBJECT) as Station
                RQ_PICK_DEPARTURE_STATION -> viewModel.departureStation.value = data?.getSerializableExtra(ARG_OBJECT) as Station
            }
        }
    }

}