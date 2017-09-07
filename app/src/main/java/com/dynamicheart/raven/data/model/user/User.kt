package com.dynamicheart.raven.data.model.user

/**
 * Created by dynamicheart on 20/8/2017.
 */
data class User(
        val id: Long,
        var username: String,
        var email: String,
        var phoneNumber: String,
        val avatar: String)