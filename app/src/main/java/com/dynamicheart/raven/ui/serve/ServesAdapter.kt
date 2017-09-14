package com.dynamicheart.raven.ui.serve

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.DataManager
import com.dynamicheart.raven.data.model.serve.Serve
import com.dynamicheart.raven.util.ToastHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ServesAdapter
@Inject constructor(private val dataManager: DataManager,
                    private val toastHelper: ToastHelper) : RecyclerView.Adapter<ServesAdapter.ServeViewHolder>() {

    var serves = ArrayList<Serve>()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.partial_list_item_serve, parent, false)
        context = parent.context
        return ServeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ServeViewHolder, position: Int) {
        val serve = serves[position]
        holder.textManName.text = serve.man?.username
        holder.textContent.text = serve.content
        holder.textTemplate.text = context.getText(R.string.serve_ordinary)
        holder.buttonAccept.setOnClickListener {
            val serveId = serve.id
            if (serveId != null) {
                dataManager.handleServe(serveId, 1)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    toastHelper.showShortToast(R.string.handle_serve_success)
                                    serves.removeAt(position)
                                    notifyDataSetChanged()
                                },
                                onError = {
                                    toastHelper.showShortToast(R.string.handle_serve_fail)
                                }
                        )
            }
        }
        holder.buttonReject.setOnClickListener {
            val serveId = serve.id
            if (serveId != null) {
                dataManager.handleServe(serveId, 0)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeBy(
                                onNext = {
                                    toastHelper.showShortToast(R.string.handle_serve_success)
                                    serves.removeAt(position)
                                    notifyDataSetChanged()
                                },
                                onError = {
                                    toastHelper.showShortToast(R.string.handle_serve_fail)
                                }
                        )
            }
        }
    }

    override fun getItemCount(): Int = serves.size

    inner class ServeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.text_man_name) lateinit var textManName: TextView
        @BindView(R.id.text_template) lateinit var textTemplate: TextView
        @BindView(R.id.text_content) lateinit var textContent: TextView
        @BindView(R.id.button_accept) lateinit var buttonAccept: Button
        @BindView(R.id.button_reject) lateinit var buttonReject: Button

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}