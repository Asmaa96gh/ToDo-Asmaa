package com.tuwaiq.todo_asmaa.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tuwaiq.todo_asmaa.R
import com.tuwaiq.todo_asmaa.model.Task
import com.tuwaiq.todo_asmaa.view.MainViewModel
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, task_Item_fragment.newInstance())
                .commitNow()


        }
    }

}