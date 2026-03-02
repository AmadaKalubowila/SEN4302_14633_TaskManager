package com.example.taskmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskmanager.R;
import com.example.taskmanager.data.Task;

public class AddTask extends AppCompatActivity {

    private EditText editTitle, editDesc;
    private Task taskToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        Button btnSave = findViewById(R.id.btnSave);


        if (savedInstanceState != null) {
            editTitle.setText(savedInstanceState.getString("title"));
            editDesc.setText(savedInstanceState.getString("desc"));
        }


        taskToEdit = (Task) getIntent().getSerializableExtra("task");
        if (taskToEdit != null) {
            editTitle.setText(taskToEdit.title);
            editDesc.setText(taskToEdit.description);
        }

        btnSave.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();

        if (title.isEmpty()) {
            editTitle.setError("Title is required");
            return;
        }

        if (taskToEdit != null) {

            taskToEdit.title = title;
            taskToEdit.description = desc;
        } else {

            taskToEdit = new Task(title, desc);
        }

        Intent data = new Intent();
        data.putExtra("task", taskToEdit);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", editTitle.getText().toString());
        outState.putString("desc", editDesc.getText().toString());
    }
}