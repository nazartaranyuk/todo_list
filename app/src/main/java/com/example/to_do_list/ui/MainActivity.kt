package com.example.to_do_list.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.to_do_list.R
import com.example.to_do_list.ui.main_fragment.MainFragment

class MainActivity : AppCompatActivity() {
    lateinit var manager: FragmentManager
    lateinit var transaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = supportFragmentManager
        transaction = manager.beginTransaction()
        transaction.add(R.id.fc_main_fragment_container, MainFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}