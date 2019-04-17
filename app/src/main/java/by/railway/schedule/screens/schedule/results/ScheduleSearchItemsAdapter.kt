package by.railway.schedule.screens.schedule.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.railway.schedule.R
import by.railway.schedule.domain.models.Train
import by.railway.schedule.extensions.setViewGone
import by.railway.schedule.extensions.setViewVisible
import kotlinx.android.synthetic.main.item_search_train.view.*

class ScheduleSearchItemsAdapter(
        private val items: ArrayList<Train>
): RecyclerView.Adapter<ScheduleSearchItemsAdapter.ScheduleSearchItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleSearchItemViewHolder {
        return ScheduleSearchItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_search_train, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ScheduleSearchItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ScheduleSearchItemViewHolder(
            view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(train: Train) {
            with(itemView) {
                tv_train_name.text = "(${train.trainNumber}) ${train.name}"
                tv_departure_time.text = train.departureTime.substring(0,5)
                tv_arrive_time.text = train.arrivingTime.substring(0,5)
                iv_logo.setImageResource(train.iconResId)
                tv_duration.text = train.totalTime
                if (train.vagons != null) {
                    wagons_list.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter =WagonsListAdapter(train.vagons)
                        setViewVisible()
                    }
                    divider.setViewVisible()
                } else {
                    wagons_list.setViewGone()
                    divider.setViewGone()
                }

            }
        }

    }

}