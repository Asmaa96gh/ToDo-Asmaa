package com.tuwaiq.todo_asmaa.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.view.MainViewModel
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        getSupportActionBar()?.setDisplayShowTitleEnabled(false)

    }

}