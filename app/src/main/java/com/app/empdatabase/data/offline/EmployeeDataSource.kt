package com.app.empdatabase.data.offline

import android.util.Log
import com.app.empdatabase.core.AppModule
import com.app.empdatabase.data.model.Employee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception

class EmployeeDataSource(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val employeeDao: EmployeeDao = AppModule.empDao
) {
    suspend fun getListOfEmployees(): Flow<Result<List<Employee>>> {
        return employeeDao.getAllEmployee().map { it -> Result.success(it) }
            .catch { it -> Result.failure<List<Employee>>(it) }.flowOn(ioDispatcher)
    }

    suspend fun addEmployeeRecord(employee: Employee) {
        withContext(ioDispatcher) {
            try {
                employeeDao.addEmployee(employee)
            } catch (exe: Exception) {
                Log.d(toString(), exe.toString())
            }
        }
    }
}
