package com.dynamicheart.raven.ui.main.conferbox

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import javax.inject.Inject

class ConfersAdapter
@Inject constructor(): RecyclerView.Adapter<ConfersAdapter.ConferViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_confer_box, parent, false)
        return ConferViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConferViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 0

    inner class ConferViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_house_name) lateinit var textConferName: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}