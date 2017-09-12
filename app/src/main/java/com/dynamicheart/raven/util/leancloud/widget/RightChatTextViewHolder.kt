package com.dynamicheart.raven.util.leancloud.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.avos.avoscloud.im.v2.AVIMMessage
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage
import com.dynamicheart.raven.R
import com.dynamicheart.raven.util.eventbus.im.ImTypeMessageResendEvent
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*

class RightChatTextViewHolder(context: Context, root: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.partial_right_chat_text, root, false)) {


    @BindView(R.id.text_time)lateinit var textTime: TextView
    @BindView(R.id.text_content)lateinit var textContent: TextView
    @BindView(R.id.text_name)lateinit var textName: TextView
    @BindView(R.id.frame_layout_status)lateinit var frameLayoutStatus: FrameLayout
    @BindView(R.id.progressbar) lateinit var progressBar: ProgressBar
    @BindView(R.id.image_error) lateinit var imageError: ImageView

    private var message: AVIMMessage? = null
    val context: Context
        get() = itemView.context

    init {
        ButterKnife.bind(this, itemView)
    }

    @OnClick(R.id.image_error)
    fun onErrorClick(view: View) {
        val event = ImTypeMessageResendEvent()
        event.message = message
        EventBus.getDefault().post(event)
    }

    fun bindData(message: AVIMMessage) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        val time = dateFormat.format(message.timestamp)

        var content = context.getString(R.string.chat_unsupport_message_type)
        if (message is AVIMTextMessage) {
            content = (message as AVIMTextMessage).text
        }

        textContent.text = content
        textTime.text = time
        textName.text = message.from

        when {
            AVIMMessage.AVIMMessageStatus.AVIMMessageStatusFailed == message.messageStatus -> {
                imageError.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                frameLayoutStatus.visibility = View.VISIBLE
            }
            AVIMMessage.AVIMMessageStatus.AVIMMessageStatusSending == message.messageStatus -> {
                imageError.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                frameLayoutStatus.visibility = View.VISIBLE
            }
            else -> frameLayoutStatus.visibility = View.GONE
        }
    }

    fun showTimeView(isShow: Boolean) {
        textTime.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}