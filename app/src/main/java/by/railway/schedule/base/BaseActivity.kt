package by.railway.schedule.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import by.railway.schedule.App
import by.railway.schedule.di.component.ApplicationComponent
import by.railway.schedule.screens.main.MainActivity
import by.railway.schedule.screens.schedule.station.SearchStationActivity

abstract class BaseActivity: AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        when(this) {
            is SearchStationActivity -> getAppComponent().inject(this)
            is MainActivity -> getAppComponent().inject(this)
        }
        initViewModel()

    }

    fun getAppComponent(): ApplicationComponent = (applicationContext as App).applicationComponent


}