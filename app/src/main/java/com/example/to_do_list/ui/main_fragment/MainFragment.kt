package com.example.to_do_list.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.to_do_list.databinding.FragmentMainBinding
import com.example.to_do_list.ui.TaskListAdapter
import com.example.to_do_list.widgets.AddTaskDialog

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
        updateListAction.observe(viewLifecycleOwner) {
            taskListAdapter.tasks = it
        }
        addTaskAction.observe(viewLifecycleOwner) {
            taskListAdapter.addTask(it)
        }

        onButtonClick.observe(viewLifecycleOwner) {
            AddTaskDialog {
                viewModel.addTask(it)

            }.show(parentFragmentManager, "my_dialog")
        }
    }
}