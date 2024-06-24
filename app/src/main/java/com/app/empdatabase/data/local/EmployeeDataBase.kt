package com.app.empdatabase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.empdatabase.data.model.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDataBase : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDao
}

internal fun getDataBase(context: Context): EmployeeDao {
    val database =
        Room.databaseBuilder(
            context,
            EmployeeDataBase::class.java,
            "emp.db",
        ).build()

    return database.getEmployeeDao()
}
