package com.tuwaiq.todo_asmaa

import android.app.AlertDialog
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todo_asmaa.model.Task
import java.time.LocalDateTime

import java.time.format.DateTimeFormatter

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.tuwaiq.todo_asmaa.Repo.ToDo_Repo
import com.tuwaiq.todo_asmaa.UI.first_fragment
import com.tuwaiq.todo_asmaa.UI.first_fragmentDirections
import com.tuwaiq.todo_asmaa.view.MainViewModel


class TaskListAdapter (private var tasks: List<Task> ,val viewModel:MainViewModel) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item_fragment,parent,false)
        return CustomAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.duedateTextView.text = task.DueDateTime
        val checkbox = holder.stateCheckBox
        // show the state by showing an image with diff colors (red =pass time and incomplete
        // yellow == available and incomplete , green = complete
        val nowDateTime = convertDateToString(LocalDateTime.now())
        val compDueToNow: Boolean = task.DueDateTime >= nowDateTime

        if(!compDueToNow){
            holder.stateShape.setImageResource(R.drawable.greencircle)
            val cardV=holder.itemView.findViewById<CardView>(R.id.cardView)
            val newcolor =holder.itemView.resources.getColor(R.color.teal_700)
            cardV.setCardBackgroundColor(newcolor)
            if(task.state){
                checkbox.isChecked = true
                checkbox.isEnabled = false

            }else{
                checkbox.isChecked = false
                checkbox.isEnabled = false
                checkbox.text="incopmlete"
                holder.stateShape.setImageResource(R.drawable.redcircle)
            }

        }else{
            checkbox.setOnCheckedChangeListener{_,ischecked ->
                if(ischecked){
                    viewModel.updatethestate(true, task.id)
                    holder.stateShape.setImageResource(R.drawable.greencircle)
                }else{
                    viewModel.updatethestate(false, task.id)
                    holder.stateShape.setImageResource(R.drawable.yellowcircle)
                }
            }

        }



        //delete task
        holder.deleteTask.setOnClickListener {
            val alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Warning!")
            alert.setMessage("Are you sure you want to delete this task?")
            alert.setPositiveButton("Yes") { dialog, which ->
                tasks -= task
                viewModel.deleteTask(task)
                notifyItemRemoved(position)
            }
            alert.setNegativeButton("No!") { dialog, which ->
                dialog.cancel()
            }
            alert.show()
        }

        holder.itemView.setOnClickListener { view ->
             val action= first_fragmentDirections.actionFirstFragmentToShowDetails(task)
            view.findNavController().navigate(action)

        }
    }
    override fun getItemCount(): Int {
return tasks.size
    }

    fun convertDateToString(Date:LocalDateTime):String{
        val localDate: LocalDateTime= LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")
        val formattedCreationDate: String = localDate.format(formatter)
      return formattedCreationDate
    }
}


class CustomAdapter {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

        // item view here is from the on create function where we created9 the view
       val titleTextView: TextView =itemView.findViewById(R.id.taskTitle)
        val stateCheckBox: CheckBox =itemView.findViewById(R.id.stateCheckBox)
        val duedateTextView: TextView =itemView.findViewById(R.id.taskDuedate)
        val stateShape:ImageView=itemView.findViewById(R.id.stateShape)
        val deleteTask :ImageView=itemView.findViewById(R.id.deleteTask)


    }
}