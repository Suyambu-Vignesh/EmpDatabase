package com.app.empdatabase.data

import com.app.empdatabase.data.local.EmployeeDataSource
import com.app.empdatabase.data.model.Employee
import kotlinx.coroutines.flow.Flow

class EmployeeRepository(
    private val dataSource: EmployeeDataSource = EmployeeDataSource(),
) {
    suspend fun getListOfEmployee(): Flow<Result<List<Employee>>> {
        return dataSource.getListOfEmployees()
    }

    suspend fun addEmployeeRecord(employee: Employee) {
        dataSource.addEmployeeRecord(employee)
    }

    suspend fun getListOfEmployeeWithName(prefixName: String): Flow<Result<List<Employee>>> {
        return dataSource.getListOfEmployeeWithName(prefixName)
    }
}
