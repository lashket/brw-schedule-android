package by.railway.schedule.screens.schedule.results

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.railway.schedule.R
import by.railway.schedule.base.BaseFragment
import by.railway.schedule.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_results.*
import javax.inject.Inject

class SearchResultsFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchResultFragmentViewModel>

    lateinit var viewModel: SearchResultFragmentViewModel

    override val layoutResourceId: Int
        get() = R.layout.fragment_search_results

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchResultFragmentViewModel::class.java)
        with(viewModel) {
            trainsLiveData.observe(this@SearchResultsFragment, Observer {
                list.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = ScheduleSearchItemsAdapter(it)
                }

            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_route.text = "${SearchResultsFragmentArgs.fromBundle(arguments!!).arriveStation.value} - ${SearchResultsFragmentArgs.fromBundle(arguments!!).destinationStation.value}"
        viewModel.getTrains(
                SearchResultsFragmentArgs.fromBundle(arguments!!).arriveTime,
                SearchResultsFragmentArgs.fromBundle(arguments!!).arriveStation,
                SearchResultsFragmentArgs.fromBundle(arguments!!).destinationStation
        )
    }

}