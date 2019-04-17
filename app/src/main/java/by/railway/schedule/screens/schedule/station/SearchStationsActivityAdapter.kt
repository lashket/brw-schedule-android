package by.railway.schedule.screens.schedule.station

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import by.railway.schedule.R
import by.railway.schedule.base.ListItemClickListener
import by.railway.schedule.domain.models.Station
import kotlinx.android.synthetic.main.item_station_search.view.*

class SearchStationsActivityAdapter(
        private val items: ArrayList<Station>,
        private val listItemClickListener: ListItemClickListener<Station>
): RecyclerView.Adapter<SearchStationsActivityAdapter.StationItemViewHolder>(), Filterable {

    private var filteredItems = ArrayList<Station>()

    init {
        filteredItems.clear()
        filteredItems.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationItemViewHolder {
        return StationItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_station_search, parent, false))
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    override fun onBindViewHolder(holder: StationItemViewHolder, position: Int) {
        holder.bind(filteredItems[holder.adapterPosition], listItemClickListener)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                filteredItems.clear()
                if (constraint.isNullOrEmpty()) {
                    filteredItems.addAll(items)
                    filterResults.values = filteredItems
                    return filterResults
                }
                filteredItems.addAll(items.filter { it -> it.value.toLowerCase().contains(constraint.toString().toLowerCase()) } as ArrayList<Station>)
                filterResults.values = filteredItems
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = results?.values as ArrayList<Station>
                notifyDataSetChanged()
            }
        }
    }

    class StationItemViewHolder(
            view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(station: Station, listItemClickListener: ListItemClickListener<Station>) {
            with(itemView) {
                text.text = station.value
                setOnClickListener { listItemClickListener.onItemClick(station) }
            }
        }

    }

}