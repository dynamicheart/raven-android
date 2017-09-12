package com.dynamicheart.raven.util.eventbus.im

open class InputBottomBarEvent(var eventAction: Int, var tag: Any) {
    companion object {
        val INPUTBOTTOMBAR_IMAGE_ACTION = 0
        val INPUTBOTTOMBAR_CAMERA_ACTION = 1
        val INPUTBOTTOMBAR_LOCATION_ACTION = 2
        val INPUTBOTTOMBAR_SEND_TEXT_ACTION = 3
        val INPUTBOTTOMBAR_SEND_AUDIO_ACTION = 4
    }
}
