package com.dynamicheart.raven.data.model.whistleblowing.form

data class CreateWhistleBlowingForm(
        var ravenId: String?,
        var type: Int = 0,
        var description: String?
)