package by.railway.schedule.extensions

import android.view.View
import android.view.View.*
import android.widget.ImageView
import by.railway.schedule.R
import java.lang.Exception

fun View.setViewInvisible() {
    this.visibility = INVISIBLE
}

fun View.setViewGone() {
    this.visibility = GONE
}

fun View.setViewVisible() {
    this.visibility = VISIBLE
}