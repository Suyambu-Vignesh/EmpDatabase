package com.app.empdatabase.core

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.empdatabase.data.local.EmployeeDao
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppModuleTest {
    @Test
    fun `test database`() {
        AppModule.init(InstrumentationRegistry.getInstrumentation().context)
        assertThat(AppModule.empDao).isInstanceOf(EmployeeDao::class.java)
    }
}
