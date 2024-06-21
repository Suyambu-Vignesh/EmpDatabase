package com.app.empdatabase.ui.model

import com.app.empdatabase.data.model.Employee

data class PageState(
    val showProgress: Boolean = true,
    val employees: List<Employee>? = null,
    val errorState: ErrorState? = null
)

enum class ErrorState(type: Int) {
    ERROR_EMPTY(0),
    ERROR_OTHER(1)
}
