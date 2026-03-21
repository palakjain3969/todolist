package com.example.todo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Adapter.ToDoAdapter;
import com.example.todo.Model.ToDoModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


    tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
    tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    tasksAdapter = new ToDoAdapter(this);
    tasksRecyclerView.setAdapter(tasksAdapter);
    }
}