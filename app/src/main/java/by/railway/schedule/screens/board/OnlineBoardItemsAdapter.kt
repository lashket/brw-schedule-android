package by.railway.schedule.screens.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.railway.schedule.R
import by.railway.schedule.domain.models.Train
import by.railway.schedule.extensions.setViewInvisible
import by.railway.schedule.extensions.setViewVisible
import kotlinx.android.synthetic.main.item_list_online_board.view.*

class OnlineBoardItemsAdapter(
    private val items: ArrayList<Train>
): RecyclerView.Adapter<OnlineBoardItemsAdapter.OnlineBoardItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineBoardItemViewHolder {
        return OnlineBoardItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_online_board, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OnlineBoardItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class OnlineBoardItemViewHolder(
            view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(train: Train) {
            with(itemView) {
                name.text = train.name
                tv_platform_number.text = train.platformNumber
                tv_way_number.text = train.lineNumber
                tv_train_number.text = train.trainNumber
                train_icon.setImageResource(train.iconResId)
                if (train.arrivingTime.isNullOrEmpty()) {
                    tv_arrive_time.text = "-"

                } else {
                    tv_arrive_time.setViewVisible()
                    tv_arrive_title.setViewVisible()
                    tv_arrive_time.text = train.arrivingTime
                }
                if (train.departureTime.isNullOrEmpty()) {
                    tv_departure_time.text = "-"
                } else {
                    tv_departure_time.setViewVisible()
                    tv_departure_title.setViewVisible()
                    tv_departure_time.text = train.departureTime
                }

            }
        }

    }

}