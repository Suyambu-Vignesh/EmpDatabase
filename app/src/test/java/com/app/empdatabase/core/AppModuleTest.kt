package com.app.empdatabase.core

import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.empdatabase.data.offline.EmployeeDataBase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Test

class AppModuleTest {

    @Test
    fun `test database`() {
        mockkStatic(Room::class)

        val builder = mockk<RoomDatabase.Builder<EmployeeDataBase>>()
        val empDatabase = mockk<EmployeeDataBase>()

        val room = mockk<Room>()
        every { builder.build() } returns empDatabase
        every { room.databaseBuilder(any(), EmployeeDataBase::class.java, , any()) } returns builder

        AppModule.empDao
    }
}