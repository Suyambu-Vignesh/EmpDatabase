package com.app.empdatabase

import android.app.Application
import com.app.empdatabase.core.AppModule

class EmpDatabaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}
