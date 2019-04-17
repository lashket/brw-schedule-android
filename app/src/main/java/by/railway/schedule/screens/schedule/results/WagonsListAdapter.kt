package by.railway.schedule.screens.schedule.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.railway.schedule.R
import by.railway.schedule.domain.models.WagonTypeModel
import kotlinx.android.synthetic.main.item_tickets_count.view.*

class WagonsListAdapter(
    private val items: ArrayList<WagonTypeModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_HEADER = 1
    private val VIEW_TYPE_ROW = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_HEADER) {
            return WagonItemHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tickets_count_header, parent, false))
        }
        return WagonItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tickets_count, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != 0) {
            (holder as WagonItemViewHolder).bind(items[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0) {
            return VIEW_TYPE_HEADER
        }
        return VIEW_TYPE_ROW
    }


    class WagonItemHeaderViewHolder(
            view: View
    ): RecyclerView.ViewHolder(view)

    class WagonItemViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(wagonTypeModel: WagonTypeModel) {
            with(itemView) {
                tv_wagon_type.text = wagonTypeModel.type
                tv_price.text = wagonTypeModel.price
                tv_count.text = wagonTypeModel.count
            }
        }

    }

}