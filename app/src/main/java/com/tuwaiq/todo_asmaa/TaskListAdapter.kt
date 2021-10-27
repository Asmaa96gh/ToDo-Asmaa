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
import com.tuwaiq.todo_asmaa.Repo.ToDo_Repo
import com.tuwaiq.todo_asmaa.view.MainViewModel


class TaskListAdapter (private var tasks: List<Task> ,val viewModel:MainViewModel) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item_fragment,parent,false)
        return CustomAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val task=tasks[position]
        holder.titleTextView.text=task.title
        holder.duedateTextView.text=task.DueDateTime
         val checkbox=holder.stateCheckBox
        // show the state by showing an image with diff colors (red =pass time and incomplete
        // yellow == available and incomplete , green = complete
        val nowDateTime=convertDateToString(LocalDateTime.now())
        val compDueToNow :Boolean=task.DueDateTime <= nowDateTime
        if(task.state){
            if(!compDueToNow){
                checkbox.isChecked=true
                checkbox.isEnabled =false
                holder.stateShape.setImageResource(R.drawable.greencircle)
            }else{
                holder.stateShape.setImageResource(R.drawable.greencircle)
            }
        }else{
            if(!compDueToNow){
                checkbox.isEnabled =false
                holder.stateShape.setImageResource(R.drawable.redcircle)
            }else {
                if (checkbox.isChecked) {
                    viewModel.updatethestate(true, task.id)
                    holder.stateShape.setImageResource(R.drawable.greencircle)
                }else{
                    holder.stateShape.setImageResource(R.drawable.yellowcircle)

                }

            }
        }





        //delete task
        holder.deleteTask.setOnClickListener{
            val alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Warning!")
            alert.setMessage("Are you sure you want to delete this task?")
            alert.setPositiveButton("Yes"){
                    dialog, which ->
                tasks -= task
                viewModel.deleteTask(task)
                notifyItemRemoved(position)
            }
            alert.setNegativeButton("No!"){
                    dialog, which ->
                dialog.cancel()
            }
            alert.show()
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
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        // item view here is from the on create function where we created9 the view
       val titleTextView: TextView =itemView.findViewById(R.id.taskTitle)
        val stateCheckBox: CheckBox =itemView.findViewById(R.id.stateCheckBox)
        val duedateTextView: TextView =itemView.findViewById(R.id.taskDuedate)
        val stateShape:ImageView=itemView.findViewById(R.id.stateShape)
        val deleteTask :ImageView=itemView.findViewById(R.id.deleteTask)

        init {
            itemView.setOnClickListener(this) //
        }
        override fun onClick(p0: View?) {

            Toast.makeText(itemView.context,"${titleTextView.text} clicked", Toast.LENGTH_SHORT).show()
        }
    }
}