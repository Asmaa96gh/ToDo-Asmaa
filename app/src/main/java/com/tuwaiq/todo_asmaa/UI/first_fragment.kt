package com.tuwaiq.todo_asmaa.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.TaskListAdapter
import com.tuwaiq.todo_asmaa.view.MainViewModel
import android.view.MenuInflater
import android.content.Intent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class first_fragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var to_addTaskPage: ImageView
    private lateinit var deleteTask :ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.task_list_fragment, container, false)
    }
    companion object {
        fun newInstance() = first_fragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        recyclerView = view.findViewById(R.id.rvRecycleView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())





        viewModel.getAllTasks().observe(viewLifecycleOwner ,{
            recyclerView.adapter = TaskListAdapter(it,viewModel)

        })

        to_addTaskPage = view.findViewById(R.id.toaddTaskpage)
        to_addTaskPage.setOnClickListener {
            val action= first_fragmentDirections.actionFirstFragmentToAddTask()
            it.findNavController().navigate(action)
       }
    }

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.state_menu, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val now=LocalDateTime.now()
       val dateTimeOftheMoment= convertDateToString(now)
       return when (item.itemId) {
            R.id.yellow -> {
                viewModel.getAllIncompleteavailable(false,dateTimeOftheMoment).observe(viewLifecycleOwner,{
                        recyclerView.adapter = TaskListAdapter(it,viewModel)
                })
true
            }
            R.id.red -> {
viewModel.getAlloutdatedAndincompelte(false,dateTimeOftheMoment).observe(viewLifecycleOwner,{
    recyclerView.adapter = TaskListAdapter(it,viewModel)
})
true
            }
            R.id.green -> {
viewModel.getAlldoneTask(true).observe(viewLifecycleOwner,{
    recyclerView.adapter = TaskListAdapter(it,viewModel)
})
true
            }
           R.id.home -> {
               viewModel.getAllTasks().observe(viewLifecycleOwner ,{
                   recyclerView.adapter = TaskListAdapter(it,viewModel)

               })
               true
           }
           else -> false
       }
    }

    fun convertDateToString(Date: LocalDateTime):String{
        val localDate: LocalDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")
        val formattedCreationDate: String = localDate.format(formatter)
        return formattedCreationDate
    }
}