package com.app.empdatabase.core

import android.content.Context
import com.app.empdatabase.data.local.EmployeeDao
import com.app.empdatabase.data.local.getDataBase

object AppModule {
    internal lateinit var empDao: EmployeeDao

    fun init(context: Context) {
        empDao = getDataBase(context)
    }
}
