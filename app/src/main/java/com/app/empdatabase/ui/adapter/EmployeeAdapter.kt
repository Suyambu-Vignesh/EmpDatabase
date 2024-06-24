package com.app.empdatabase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.app.empdatabase.data.model.Employee
import com.app.empdatabase.databinding.ViewEmployeeRecordBinding

internal class EmployeeAdapter : ListAdapter<Employee, EmployeeViewHolder>(
    object : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(
            oldItem: Employee,
            newItem: Employee,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Employee,
            newItem: Employee,
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    },
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EmployeeViewHolder {
        return EmployeeViewHolder(
            ViewEmployeeRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    override fun onBindViewHolder(
        holder: EmployeeViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }
}

internal class EmployeeViewHolder(
    private val binding: ViewEmployeeRecordBinding,
) : ViewHolder(binding.root) {
    internal fun bind(employee: Employee) {
        binding.textName.text = employee.name
        binding.textCompany.text = employee.organization
        binding.textAddress.text = "${employee.address.state}, ${employee.address.country}"
    }
}
