package com.tuwaiq.todo_asmaa.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.todo_asmaa.Add_Task
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.TaskListAdapter
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.view.MainViewModel
import java.time.LocalDateTime

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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        /* viewModel.addTask(t)
        viewModel.addTask(t2)*/
        /*  viewModel.deleteTask(t)
        viewModel.deleteTask(t2)*/

        recyclerView = view.findViewById(R.id.rvRecycleView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        to_addTaskPage = view.findViewById(R.id.toaddTaskpage)

        viewModel.getAllTasks().observe(viewLifecycleOwner ,{
            recyclerView.adapter = TaskListAdapter(it,viewModel)

        })


        to_addTaskPage.setOnClickListener {
        requireActivity().supportFragmentManager.beginTransaction()
        .replace(R.id.container , Add_Task())
        .addToBackStack("main")
        .commit()}
    }




}