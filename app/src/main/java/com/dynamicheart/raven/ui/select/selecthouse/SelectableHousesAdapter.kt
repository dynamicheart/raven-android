package com.dynamicheart.raven.ui.select.selecthouse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.House
import javax.inject.Inject

class SelectableHousesAdapter
@Inject constructor(): RecyclerView.Adapter<SelectableHousesAdapter.SelectableHouseViewHolder>(){

    var houses = emptyList<House>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableHouseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_selectable_house, parent, false)
        return SelectableHouseViewHolder(itemView)
    }

    override fun onBindViewHolder(holderSelectable: SelectableHouseViewHolder, position: Int) {
        val house = houses[position]
        holderSelectable.textSelectableHouseName.text = house.name
    }

    override fun getItemCount(): Int = houses.size

    inner class SelectableHouseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_selectable_house_name) lateinit var textSelectableHouseName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}