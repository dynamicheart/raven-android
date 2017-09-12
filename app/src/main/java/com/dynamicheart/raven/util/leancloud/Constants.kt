package com.dynamicheart.raven.util.leancloud

object Constants{
    private val LEANMESSAGE_CONSTANTS_PREFIX = "com.dynamicheart.raven"
    val MEMBER_ID = getPrefixConstant("member_id")
    val CONVERSATION_ID = getPrefixConstant("conversation_id")

    private fun getPrefixConstant(str: String): String {
        return LEANMESSAGE_CONSTANTS_PREFIX + str
    }
}