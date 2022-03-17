package com.example.to_do_list.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.R
import com.example.to_do_list.databinding.FragmentMainBinding
import com.example.to_do_list.ui.TaskListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    private val taskListAdapter by lazy { TaskListAdapter() }
    private val viewModel by viewModels<MainViewModel>()
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding?.rvTaskList?.adapter = taskListAdapter

        viewModel.getDate()
        viewModel.getListOfTasks()

        subscribeToLiveData()
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    private fun subscribeToLiveData() = with(viewModel) {
        getCurrentData.observe(viewLifecycleOwner) {
            binding?.dateText?.text = it
        }
        listOfTask.observe(viewLifecycleOwner) {
            taskListAdapter.tasks = it
        }
        getTask.observe(viewLifecycleOwner) {
            taskListAdapter.addTask(it)
        }
    }
}