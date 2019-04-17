package by.railway.schedule.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import by.railway.schedule.App
import by.railway.schedule.di.component.ApplicationComponent
import by.railway.schedule.screens.board.OnlineBoardFragment
import by.railway.schedule.screens.schedule.ScheduleFragment
import by.railway.schedule.screens.schedule.results.SearchResultsFragment
import by.railway.schedule.screens.tickets.TicketsFragment

abstract class BaseFragment: Fragment() {

    lateinit var fragmentNavigation: FragmentNavigation

    internal var fragmentInstance = 0


    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    abstract fun initViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when(this) {
            is ScheduleFragment -> getAppComponent().inject(this)
            is OnlineBoardFragment -> getAppComponent().inject(this)
            is SearchResultsFragment -> getAppComponent().inject(this)
            is TicketsFragment -> getAppComponent().inject(this)
        }
        val args = arguments
        if (args != null) {
            fragmentInstance = args.getInt(ARGS_INSTANCE)
        }
    }

    fun getAppComponent(): ApplicationComponent = (activity?.applicationContext as App).applicationComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigation) {
            fragmentNavigation = context
        }
    }

    fun goBack() {
        activity?.onBackPressed()
    }

    companion object {
        val ARGS_INSTANCE = "com.ncapdevi.sample.argsInstance"
    }

}