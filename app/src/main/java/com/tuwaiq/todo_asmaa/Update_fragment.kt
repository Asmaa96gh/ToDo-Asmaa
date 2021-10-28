package com.tuwaiq.todo_asmaa

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.view.MainViewModel
import kotlinx.android.synthetic.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Update_fragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var btnUpdate: Button
    private lateinit var titleupdate: EditText
    private lateinit var descupdate: EditText
    private lateinit var update_btnDuedate:ImageButton
    private lateinit var update_duedate: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.update_fragment, container, false)
    }

    companion object {
   fun newInstance()=Update_fragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val args:  Update_fragmentArgs by navArgs()
        val task = args.update
        titleupdate=view.findViewById(R.id.update_title)
        descupdate=view.findViewById(R.id.update_decription)
        btnUpdate=view.findViewById(R.id.update_task_btn)
        update_btnDuedate=view.findViewById(R.id.update_btnDuedate)
        update_duedate=view.findViewById(R.id.update_duedate)

        update_btnDuedate.setOnClickListener{
            showDateTimeDialog(update_duedate)}

        titleupdate.setText(task.title)
        descupdate.setText(task.description)

        btnUpdate.setOnClickListener{
            viewModel.updateTask(Task(task.id,titleupdate.text.toString(),descupdate.text.toString(),task.creationDateTime,update_duedate.text.toString()))
            val action = Update_fragmentDirections.actionUpdateFragmentToFirstFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun showDateTimeDialog(date_time_in: TextView) {
        val calendar: Calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { it, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { it, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        val simpleDateFormat = SimpleDateFormat("yy-MM-dd HH:mm")
                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()))
                    }
                TimePickerDialog(
                    requireContext(),
                    timeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }
        DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }


}