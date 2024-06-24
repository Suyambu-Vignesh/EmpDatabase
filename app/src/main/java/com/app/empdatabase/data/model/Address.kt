package com.app.empdatabase.data.model

import androidx.room.ColumnInfo

data class Address(
    @ColumnInfo("col_streetAddressLine1")
    val streetAddressLine1: String,
    @ColumnInfo("col_streetAddressLine2")
    val streetAddressLine2: String,
    @ColumnInfo("col_cityName")
    val cityName: String,
    @ColumnInfo("col_zipCode")
    val zipCode: String,
    @ColumnInfo("col_state")
    val state: String,
    @ColumnInfo("col_country")
    val country: String,
)
