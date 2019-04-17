package by.railway.schedule.screens.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.railway.schedule.R
import by.railway.schedule.base.BaseActivity
import by.railway.schedule.base.BaseViewModel
import by.railway.schedule.base.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainActivityViewModel>

    lateinit var viewModel: MainActivityViewModel

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        viewModel.statusLiveData.observe(this, Observer {
            if (it == BaseViewModel.Status.COMPLETE) {
                Toast.makeText(this, getString(R.string.ticket_added_to_db), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.ticket_not_added_to_db), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val controller = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        navigation.apply {
            setupWithNavController(controller)
            setOnNavigationItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this@MainActivity, R.id.my_nav_host_fragment))
            }
        }
        intent.apply {
            if (hasExtra(Intent.EXTRA_STREAM) && type != null && type!!.startsWith("image/")) {
                viewModel.handleIncomingIntent(this, this@MainActivity)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }


}
