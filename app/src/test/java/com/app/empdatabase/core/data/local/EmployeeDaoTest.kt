package com.app.empdatabase.core.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.empdatabase.core.utils.test
import com.app.empdatabase.data.local.EmployeeDao
import com.app.empdatabase.data.local.EmployeeDataBase
import com.app.empdatabase.data.model.Address
import com.app.empdatabase.data.model.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmployeeDaoTest {
    private lateinit var database: EmployeeDataBase
    private lateinit var employeeDao: EmployeeDao
    private val dispatcher = StandardTestDispatcher()

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
        runTest(context = dispatcher) {
            withContext(Dispatchers.IO) {
                val scope = TestScope(dispatcher)
                val flowCollector =
                    employeeDao.getAllEmployee().test(scope)

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

                dispatcher.scheduler.advanceUntilIdle()
                flowCollector.assertSize(1)

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
                dispatcher.scheduler.advanceUntilIdle()
                flowCollector.assertSize(2)
            }
        }

    @After
    fun onFinish() {
        database.close()
    }
}
