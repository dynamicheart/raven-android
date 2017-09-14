package com.dynamicheart.raven.util.eventbus.im

class InputBottomBarTextEvent(action: Int,
                              var sendContent: String, tag: Any) : InputBottomBarEvent(action, tag)
