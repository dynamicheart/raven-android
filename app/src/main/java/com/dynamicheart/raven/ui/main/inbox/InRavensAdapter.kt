package com.dynamicheart.raven.ui.main.inbox

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.raven.InRaven
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject

class InRavensAdapter
@Inject constructor() : RecyclerView.Adapter<InRavensAdapter.InRavenViewHolder>() {

    var inRavens = emptyList<InRaven>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InRavenViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_inraven, parent, false)
        return InRavenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InRavenViewHolder, position: Int) {
        val inRaven = inRavens[position]
        holder.textAddresser.text = inRaven.addresser?.username
        holder.textHouse.text = inRaven.house?.name
        holder.textDescription.text = inRaven.description
    }

    override fun getItemCount(): Int = inRavens.size

    inner class InRavenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.image_in_raven_addresser) lateinit var imageAddresser: CircleImageView
        @BindView(R.id.text_in_raven_addresser) lateinit var textAddresser: TextView
        @BindView(R.id.text_in_raven_house) lateinit var textHouse: TextView
        @BindView(R.id.text_in_raven_description) lateinit var textDescription: TextView
        @BindView(R.id.text_in_raven_send_time) lateinit var textSendTime: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}