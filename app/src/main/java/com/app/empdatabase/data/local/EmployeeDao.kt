package com.app.empdatabase.data.local

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

    @Query("SELECT * FROM table_employee WHERE col_name LIKE :prefixName || '%'")
    fun getAllEmployeeWithName(prefixName: String): Flow<List<Employee>>
}
