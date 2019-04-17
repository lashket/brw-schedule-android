package by.railway.schedule.screens.tickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.railway.schedule.R
import by.railway.schedule.domain.models.Ticket
import by.railway.schedule.utils.DateUtils
import kotlinx.android.synthetic.main.item_list_ticket.view.*

class TicketsListAdapter(
        private val items: ArrayList<Ticket>
): RecyclerView.Adapter<TicketsListAdapter.TicketItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItemViewHolder {
        return TicketItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_ticket, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TicketItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class TicketItemViewHolder(
            view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(ticket: Ticket) {
            with(itemView) {
                tv_train_name.text = "(${ticket.trainNumber}) ${ticket.trainRoute}"
                tv_departure_date.text = "Отправление - ${DateUtils.getTicketTime(ticket.departureTime)}"
                tv_seats_count.text = "Места - ${ticket.seats}"
                tv_wagon_number.text = "Вагон - ${ticket.wagonNumber}"
            }
        }

    }

}