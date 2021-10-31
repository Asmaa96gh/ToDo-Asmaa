package com.tuwaiq.todo_asmaa.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.tuwaiq.todo_asmaa.R
import kotlinx.android.synthetic.main.show_details_fragment.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class show_details : Fragment() {

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

        view.show_title.text="Title : ${task.title}"
        view.show_decription.text="description: ${task.description}"
        view.show_duedate.text="Due date: ${task.DueDateTime}"
        view.show_creationDate.text="Creation time: ${task.creationDateTime}"



            view.edit_task_btn.setOnClickListener{
                val action = show_detailsDirections.actionShowDetailsToUpdateFragment(task)
                view.findNavController().navigate(action)
        }


}



    fun convertDateToString(Date: LocalDateTime):String{
        val localDate: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")
        val formattedCreationDate: String = localDate.format(formatter)
        return formattedCreationDate
    }
}