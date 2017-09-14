package com.dynamicheart.raven.ui.main.house

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.house.House
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject

class HousesAdapter
@Inject constructor(): RecyclerView.Adapter<HousesAdapter.HouseViewHolder>(){

    var houses = emptyList<House>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_house, parent, false)
        return HouseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val house = houses[position]
        holder.textHouseName.text = house.name
    }

    override fun getItemCount(): Int = houses.size

    inner class HouseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_house_name) lateinit var textHouseName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}