package com.app.empdatabase.core.data

import com.app.empdatabase.data.EmployeeRepository
import com.app.empdatabase.data.local.EmployeeDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Testsuite of [EmployeeRepository]
 */
class EmployeeRepositoryTest {
    @Test
    fun `test getListOfEmployee calls datasources getListOfEmployee`(): Unit =
        runBlocking {
            val mockDataSource = mockk<EmployeeDataSource>()

            coEvery { mockDataSource.getListOfEmployees() } returns mockk()

            val employeeRepository = EmployeeRepository(mockDataSource)

            employeeRepository.getListOfEmployee()

            coVerify(exactly = 1) { mockDataSource.getListOfEmployees() }
        }

    @Test
    fun `test addEmployeeRecord calls datasources addEmployeeRecord`(): Unit =
        runBlocking {
            val mockDataSource = mockk<EmployeeDataSource>()

            coEvery { mockDataSource.addEmployeeRecord(any()) } returns Unit

            val employeeRepository = EmployeeRepository(mockDataSource)

            employeeRepository.addEmployeeRecord(mockk())

            coVerify(exactly = 1) { mockDataSource.addEmployeeRecord(any()) }
        }

    @Test
    fun `test getListOfEmployeeWithName calls datasources getListOfEmployeeWithName`(): Unit =
        runBlocking {
            val mockDataSource = mockk<EmployeeDataSource>()
            coEvery { mockDataSource.getListOfEmployeeWithName("name") } returns mockk()

            val employeeRepository = EmployeeRepository(mockDataSource)

            employeeRepository.getListOfEmployeeWithName("name")

            coVerify(exactly = 1) { mockDataSource.getListOfEmployeeWithName(any()) }
        }
}
