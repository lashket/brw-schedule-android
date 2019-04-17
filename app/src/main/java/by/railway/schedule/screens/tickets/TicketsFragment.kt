package by.railway.schedule.screens.tickets

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.railway.schedule.R
import by.railway.schedule.base.BaseFragment
import by.railway.schedule.base.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tickets.*
import javax.inject.Inject

class TicketsFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<TicketsFragmentViewModel>

    lateinit var viewModel: TicketsFragmentViewModel

    override val layoutResourceId: Int
        get() = R.layout.fragment_tickets

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TicketsFragmentViewModel::class.java)
        viewModel.ticketsLiveData.observe(this, Observer {
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TicketsListAdapter(it)
            }
        })
    }

}