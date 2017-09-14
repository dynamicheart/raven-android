package com.dynamicheart.raven.data.model.house.form

data class UpdateHouseForm(
        var name: String?,
        var description: String?,
        var tags: List<String>?
)