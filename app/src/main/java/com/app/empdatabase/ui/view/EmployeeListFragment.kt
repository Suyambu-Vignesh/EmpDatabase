package com.app.empdatabase.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.app.empdatabase.databinding.FrgamentEmployeeListBinding
import com.app.empdatabase.ui.EmployeeViewModel
import com.app.empdatabase.ui.adapter.EmployeeAdapter
import com.app.empdatabase.ui.model.ErrorState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EmployeeListFragment : Fragment() {
    private lateinit var binding: FrgamentEmployeeListBinding
    private val viewModel by activityViewModels<EmployeeViewModel>()
    private val adapter: EmployeeAdapter by lazy { EmployeeAdapter() }

    init {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pageState.collectLatest {
                    binding.viewProgress.visibility =
                        if (it.showProgress) View.VISIBLE else View.GONE

                    it.errorState?.let { errorState ->
                        binding.viewError.root.visibility = View.VISIBLE

                        if (errorState == ErrorState.ERROR_EMPTY) {
                            // todo move to string to string.xml
                            binding.viewError.textMessage.text =
                                "Tap Add Employee, to add new Employee Record"
                            binding.viewError.textTitle.text = "No Record !!!"
                        } else {
                            binding.viewError.textMessage.text =
                                "We are on top on it, Please try after sometime"
                            binding.viewError.textTitle.text = "Something Went Wrong !!!"
                        }

                    } ?: run {
                        binding.viewError.root.visibility = View.GONE
                    }

                    binding.viewEmployeeList.visibility = if (it.employees.isNullOrEmpty()) {
                        View.GONE
                    } else {
                        adapter.submitList(it.employees)
                        View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrgamentEmployeeListBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewEmployeeList.adapter = adapter
        viewModel.fetchEmployeeRecords()
    }
}