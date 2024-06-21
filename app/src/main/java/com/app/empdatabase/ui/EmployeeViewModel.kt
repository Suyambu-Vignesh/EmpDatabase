package com.app.empdatabase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.empdatabase.data.EmployeeRepository
import com.app.empdatabase.data.model.Employee
import com.app.empdatabase.ui.model.ErrorState
import com.app.empdatabase.ui.model.PageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EmployeeViewModel(
    private val repository: EmployeeRepository = EmployeeRepository()
) : ViewModel() {
    private val state: MutableStateFlow<PageState> by lazy {
        MutableStateFlow<PageState>(
            PageState()
        )
    }

    internal val pageState: StateFlow<PageState> by lazy {
        state
    }

    internal fun fetchEmployeeRecords() {
        viewModelScope.launch {
            repository.getListOfEmployee().collectLatest {
                val empRecords = it.getOrNull()

                state.value = PageState(
                    showProgress = false,
                    employees = if (empRecords.isNullOrEmpty()) null else empRecords,
                    errorState = if (empRecords.isNullOrEmpty()) ErrorState.ERROR_EMPTY else null
                )
            }
        }
    }

    internal fun addEmployeeRecord(empRecord: Employee) {
        viewModelScope.launch {
            repository.addEmployeeRecord(empRecord)
        }
    }
}