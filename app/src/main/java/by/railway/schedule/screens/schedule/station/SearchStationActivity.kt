package by.railway.schedule.screens.schedule.station

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.railway.schedule.R
import by.railway.schedule.base.BaseActivity
import by.railway.schedule.base.ListItemClickListener
import by.railway.schedule.base.ViewModelFactory
import by.railway.schedule.domain.models.Station
import by.railway.schedule.utils.ARG_OBJECT
import kotlinx.android.synthetic.main.activity_search_station.*
import javax.inject.Inject

class SearchStationActivity: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchStationActivityViewModel>

    lateinit var viewModel: SearchStationActivityViewModel

    override val layoutResourceId: Int
        get() = R.layout.activity_search_station

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchStationActivityViewModel::class.java)
        viewModel.homeData.observe(this, Observer {
            list.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = SearchStationsActivityAdapter(it, object : ListItemClickListener<Station> {
                    override fun onItemClick(item: Station) {
                        setResult(Activity.RESULT_OK, Intent().apply {
                            putExtra(ARG_OBJECT, item)
                        })
                        finish()
                    }
                })
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        et_search_query.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                (list.adapter as SearchStationsActivityAdapter).filter.filter(s)
            }
        })
    }

    companion object {
        fun showForResult(fragment: Fragment, requestCode: Int) = fragment.startActivityForResult(
                Intent(fragment.context, SearchStationActivity::class.java),
                requestCode
        )
    }

}