package com.app.empdatabase.data.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.empdatabase.data.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM table_employee")
    fun getAllEmployee(): Flow<List<Employee>>

    @Insert
    fun addEmployee(employee: Employee)
}
