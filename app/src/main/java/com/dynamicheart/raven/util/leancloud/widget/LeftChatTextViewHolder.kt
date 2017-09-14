package com.dynamicheart.raven.util.leancloud.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.avos.avoscloud.im.v2.AVIMMessage
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage
import com.dynamicheart.raven.R
import com.dynamicheart.raven.util.eventbus.im.LeftChatItemClickEvent
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*

class LeftChatTextViewHolder(context: Context, root: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.partial_left_chat_text, root, false)) {

    @BindView(R.id.text_time) lateinit var textTime: TextView
    @BindView(R.id.text_content) lateinit var textContent: TextView
    @BindView(R.id.text_name) lateinit var textName: TextView

    val context: Context
        get() = itemView.context

    init {
        ButterKnife.bind(this, itemView)
    }

    @OnClick(R.id.text_content, R.id.text_name)
    fun onNameClick(view: View) {
        val clickEvent = LeftChatItemClickEvent(textName.text.toString())
        EventBus.getDefault().post(clickEvent)
    }

    fun bindData(message: AVIMMessage) {
        val dateFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
        val time = dateFormat.format(message.timestamp)

        var content = context.getString(R.string.chat_unsupport_message_type)
        if (message is AVIMTextMessage) {
            content = message.text
        }

        textContent.text = content
        textTime.text = time
        textName.text = message.from
    }

    fun showTimeView(isShow: Boolean) {
        textTime.visibility = if (isShow) View.VISIBLE else View.GONE
    }

}