package com.app.empdatabase.core.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.empdatabase.data.local.EmployeeDao
import com.app.empdatabase.data.local.EmployeeDataBase
import com.app.empdatabase.data.model.Address
import com.app.empdatabase.data.model.Employee
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmployeeDaoTest {
    private lateinit var database: EmployeeDataBase
    private lateinit var employeeDao: EmployeeDao

    @Before
    fun onStart() {
        database =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                EmployeeDataBase::class.java,
            ).build()

        employeeDao = database.getEmployeeDao()
    }

    @Test
    fun `test getAllEmployees`(): Unit =
        runTest {
            var listOfEmployees: List<Employee> = ArrayList<Employee>()

            val values = mutableListOf<Employee>()
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                employeeDao.getAllEmployee().collect {
                    listOfEmployees = it
                }
            }

            assertThat(listOfEmployees.size).isEqualTo(0)

            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                employeeDao.addEmployee(
                    Employee(
                        name = "name1",
                        organization = "org",
                        address =
                            Address(
                                "line1",
                                "line2",
                                "city1",
                                "zip1",
                                "state1",
                                "country1",
                            ),
                    ),
                )

                advanceUntilIdle()

                assertThat(listOfEmployees.size).isEqualTo(1)

                employeeDao.addEmployee(
                    Employee(
                        name = "name2",
                        organization = "org",
                        address =
                            Address(
                                "line1",
                                "line2",
                                "city1",
                                "zip1",
                                "state1",
                                "country1",
                            ),
                    ),
                )

                assertThat(listOfEmployees.size).isEqualTo(2)
            }
        }

    @After
    fun onFinish() {
        database.close()
    }
}
