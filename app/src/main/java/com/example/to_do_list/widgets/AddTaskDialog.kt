package com.example.to_do_list.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.to_do_list.databinding.AddTaskDialogFragmentBinding

class AddTaskDialog(private var listener: (String) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return AddTaskDialogFragmentBinding.inflate(inflater).apply {
            fabAddTaskText.setOnClickListener {
                val text = etTaskText.text.toString()
                listener(text)
                dismiss()
            }
        }.root
    }
}