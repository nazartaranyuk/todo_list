package com.example.to_do_list.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.R
import com.example.to_do_list.data.Task
import com.example.to_do_list.ui.main_fragment.MainFragment

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {
    private var mContext: Context? = null
    private var lastPosition = -1

    var tasks: MutableList<Task> = mutableListOf()


    fun addTask(task: Task) {
        tasks.add(task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tasks_list, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        if (holder.adapterPosition > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(mContext, R.anim.animation_rv)
            holder.itemView.startAnimation(animation)
            holder.createField(tasks[position])
        }
    }

    override fun getItemCount(): Int = tasks.size

    class TaskListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var checkBoxTask: CheckBox? = null
        fun createField(task: Task) {
            checkBoxTask?.text = task.name
        }

        init {
            checkBoxTask = view.findViewById(R.id.checkBox)
        }
    }
}