package com.tuwaiq.todo_asmaa.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.app.DatePickerDialog.OnDateSetListener

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.*

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.view.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.app.DatePickerDialog
import kotlinx.android.synthetic.main.add_task_fragment.view.*


class Add_Task : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var dateTimePicker:TextView
    private lateinit var title:EditText
    private lateinit var describtion:EditText
    private lateinit var btnAdd:Button
    private lateinit var btndueDate:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_task_fragment, container, false)
    }

    companion object {
        fun newInstance() = Add_Task()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        dateTimePicker=view.findViewById(R.id.user_duedate)
        btndueDate=view.findViewById(R.id.btnDuedate)
        btndueDate.setOnClickListener{
            showDateTimeDialog(dateTimePicker)}


        btnAdd=view.findViewById(R.id.add_task)
        btnAdd.setOnClickListener{
            //convert date od creation task to string to store it in the database
            val localDate: LocalDateTime= LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")
            val formattedCreationDate: String = localDate.format(formatter)

            title=view.findViewById(R.id.user_title)
            describtion=view.findViewById(R.id.user_dicription)
            if(title.text.isNotEmpty() && title.text != null){
                if(describtion.text.isNotEmpty() && describtion.text != null){
                    if(dateTimePicker.text.isNotEmpty() && dateTimePicker.text != null){
                        var uniqueID = UUID.randomUUID()
                        var taskTtile =title.text.toString()
                        val taskdesc=describtion.text.toString()
                        viewModel.addTask(Task(uniqueID.toString(),taskTtile,taskdesc,formattedCreationDate,dateTimePicker.text.toString()))
                    } else{
                        dateTimePicker.error=" mandatory!!"
                        btnAdd.isEnabled=false
                    }
                } else{describtion.error=" mandatory!!"
                    btnAdd.isEnabled=false}
            } else{title.error=" mandatory!!"
                btnAdd.isEnabled=false}

            val action= Add_TaskDirections.actionAddTaskToFirstFragment()
            it.findNavController().navigate(action)
        }

    }

    private fun showDateTimeDialog(date_time_in: TextView) {
        val calendar: Calendar = Calendar.getInstance()
        val dateSetListener =
            OnDateSetListener { it, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val timeSetListener =
                    OnTimeSetListener { it, hourOfDay, minute ->
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

    fun showdateDialog(text:TextView){
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        android.app.DatePickerDialog(requireContext(),
            OnDateSetListener { view, year, month, day ->
            text.setText("$day/$month/$year")
        }, year, month, day).show()

    }

}