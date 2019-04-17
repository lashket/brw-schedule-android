package by.railway.schedule.screens.board

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.railway.schedule.R
import by.railway.schedule.base.BaseFragment
import by.railway.schedule.base.ViewModelFactory
import by.railway.schedule.screens.schedule.results.ScheduleSearchItemsAdapter
import kotlinx.android.synthetic.main.fragment_online_board.*
import javax.inject.Inject

class OnlineBoardFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<OnlineBoardFragmentViewModel>

    lateinit var viewModel: OnlineBoardFragmentViewModel

    override val layoutResourceId: Int
        get() = R.layout.fragment_online_board

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OnlineBoardFragmentViewModel::class.java)
        viewModel.models.observe(this, Observer {
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OnlineBoardItemsAdapter(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}