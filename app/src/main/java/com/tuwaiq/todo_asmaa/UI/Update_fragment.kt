package com.tuwaiq.todo_asmaa.UI

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
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.view.MainViewModel
import kotlinx.android.synthetic.main.update_fragment.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Update_fragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    /*    private lateinit var btnUpdate: Button
        private lateinit var titleupdate: EditText
        private lateinit var descupdate: EditText
        private lateinit var update_btnDuedate:ImageButton
        private lateinit var update_duedate: TextView*/
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
        fun newInstance() = Update_fragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val args: Update_fragmentArgs by navArgs()
        val task = args.update
        val titleupdate = view.update_title
        val descupdate = view.update_decription
        val update_duedate = view.update_duedate

        view.update_btnDuedate.setOnClickListener {
            showDateTimeDialog(update_duedate)
        }

        titleupdate.setText(task.title)
        descupdate.setText(task.description)
        update_duedate.setText(task.DueDateTime)

        val nowDateTime = convertDateToString()
        val compDueToNow: Boolean = task.DueDateTime >= nowDateTime
        if (!compDueToNow) {
            titleupdate.isEnabled = false
            view.update_btnDuedate.isEnabled = false
            view.update_warning.isVisible = true
        } else {
            view.update_warning.isVisible = false
        }
        view.update_task_btn.setOnClickListener {
            viewModel.updateTask(
                Task(
                    task.id,
                    titleupdate.text.toString(),
                    descupdate.text.toString(),
                    task.creationDateTime,
                    update_duedate.text.toString()
                )
            )
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
                    R.style.dialogStyle,
                    timeSetListener,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
                ).show()
            }
        DatePickerDialog(
            requireContext(),
            R.style.dialogStyle,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun convertDateToString(): String {
        val localDate: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yy-MM-d HH:mm")
        val formattedCreationDate: String = localDate.format(formatter)
        return formattedCreationDate
    }


}