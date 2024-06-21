package com.app.empdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "col_name")
    val name: String,
    @ColumnInfo(name = "col_organization")
    val organization: String,
    @Embedded
    val address: Address
)
