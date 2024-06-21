package com.app.empdatabase.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.app.empdatabase.data.model.Address
import com.app.empdatabase.data.model.Employee
import com.app.empdatabase.databinding.FragmentNewEmployeeBinding
import com.app.empdatabase.ui.EmployeeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewEmployeeFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewEmployeeBinding
    private val viewModel by activityViewModels<EmployeeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewEmployeeBinding.inflate(
            LayoutInflater.from(requireContext())
        )

        binding.buttonAddEmployee.setOnClickListener {
            if (binding.viewNameEditText.text.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Name needed !!!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (binding.viewOrganizationEditText.text.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Organization needed !!!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // todo check all info

            val address = Address(
                streetAddressLine1 = binding.viewStreetNameOneEditText.text.toString(),
                streetAddressLine2 = binding.viewStreetNameTwoEditText.text.toString(),
                cityName = binding.viewCityText.text.toString(),
                zipCode = binding.viewZipcodeText.text.toString(),
                state = binding.viewStateText.text.toString(),
                country = binding.viewCountryText.text.toString()
            )

            val employee = Employee(
                name = binding.viewNameEditText.text.toString(),
                organization = binding.viewOrganizationEditText.text.toString(),
                address = address
            )

            viewModel.addEmployeeRecord(employee)

            this.parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}