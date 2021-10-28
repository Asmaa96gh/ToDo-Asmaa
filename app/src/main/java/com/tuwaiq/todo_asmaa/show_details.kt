package com.tuwaiq.todo_asmaa

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.show_details_fragment.*
import org.jetbrains.anko.find
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class show_details : Fragment() {

private lateinit var showTitle:TextView
    private lateinit var showdesc:TextView
    private lateinit var showdueTime:TextView
    private lateinit var showCreationTime:TextView
    private lateinit var showState:TextView
    private lateinit var btntoUpdate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.show_details_fragment, container, false)
    }

    companion object {
        fun newInstance() = show_details()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args:  show_detailsArgs by navArgs()
        val task = args.detalis
        showTitle= view.findViewById(R.id.show_title)
        showdesc=view.findViewById(R.id.show_decription)
        showdueTime=view.findViewById(R.id.show_duedate)
        showCreationTime=view.findViewById(R.id.show_creationDate)
        showState=view.findViewById(R.id.show_state)
        btntoUpdate =view.findViewById(R.id.edit_task_btn)
        showTitle.text=task.title
        showdesc.text=task.description
        showdueTime.text=task.DueDateTime
        showState.text= task.state.toString()
        showCreationTime.text=task.creationDateTime

        val nowDateTime = convertDateToString(LocalDateTime.now())
        val compDueToNow: Boolean = task.DueDateTime >= nowDateTime
        if(!compDueToNow){
            edit_task_btn.setOnClickListener{
                val alert = AlertDialog.Builder(getActivity())
                alert.setTitle("Warning!")
                alert.setMessage("you can't update ended task")
                alert.setPositiveButton("Ok"){_,_->
                    val action = show_detailsDirections.actionShowDetailsToFirstFragment()
                    view.findNavController().navigate(action)
                }.show()
            }
        }else{
            edit_task_btn.setOnClickListener{
                val action = show_detailsDirections.actionShowDetailsToUpdateFragment(task)
                view.findNavController().navigate(action)
        }


}

    }

    fun convertDateToString(Date: LocalDateTime):String{
        val localDate: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")
        val formattedCreationDate: String = localDate.format(formatter)
        return formattedCreationDate
    }
}