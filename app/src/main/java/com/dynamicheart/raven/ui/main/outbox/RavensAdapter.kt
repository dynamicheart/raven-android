package com.dynamicheart.raven.ui.main.outbox

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.raven.Raven
import javax.inject.Inject

class RavensAdapter
@Inject constructor() : RecyclerView.Adapter<RavensAdapter.RavenViewHolder>() {

    var ravens = emptyList<Raven>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RavenViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_raven, parent, false)
        return RavenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RavenViewHolder, position: Int) {
        val raven = ravens[position]
        holder.textTile.text = raven.title
        holder.textHouseName.text = raven.house?.name
        holder.textContent.text = raven.description
    }

    override fun getItemCount(): Int = ravens.size

    inner class RavenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_title) lateinit var textTile: TextView
        @BindView(R.id.text_house_name) lateinit var textHouseName: TextView
        @BindView(R.id.text_content) lateinit var textContent: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }


}