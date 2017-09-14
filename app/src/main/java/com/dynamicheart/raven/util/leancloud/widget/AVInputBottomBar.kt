package com.dynamicheart.raven.util.leancloud.widget

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.dynamicheart.raven.R
import com.dynamicheart.raven.util.eventbus.im.InputBottomBarEvent
import com.dynamicheart.raven.util.eventbus.im.InputBottomBarTextEvent
import org.greenrobot.eventbus.EventBus

class AVInputBottomBar : LinearLayout {

    private val MIN_INTERVAL_SEND_MESSAGE = 1000

    private lateinit var buttonSend: ImageButton

    private lateinit var editTextContent: EditText

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        val view = View.inflate(context, R.layout.partial_input_bottom_bar, this)

        buttonSend = view.findViewById(R.id.button_send)
        editTextContent = view.findViewById(R.id.edit_text_content)

        setEditTextChangeListener()

        buttonSend.setOnClickListener({
            val content = editTextContent.text.toString()
            if (TextUtils.isEmpty(content)) {
                TODO()
                return@setOnClickListener
            }

            editTextContent.setText("")
            Handler().postDelayed({ buttonSend.isEnabled = true }, MIN_INTERVAL_SEND_MESSAGE.toLong())

            EventBus.getDefault().post(
                    InputBottomBarTextEvent(InputBottomBarEvent.INPUTBOTTOMBAR_SEND_TEXT_ACTION, content, tag))
        })
    }

    private fun setEditTextChangeListener() {
        editTextContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}
