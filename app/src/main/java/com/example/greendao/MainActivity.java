package com.example.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText_userName;
    private EditText editText_userId;
    private EditText editText_note;
    private Button button_addUser;
    private Button button_addNote;

    private RecyclerView recyclerView_user;
    private RecyclerView recyclerView_note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_userName = findViewById(R.id.editText_user_name);
        editText_userId = findViewById(R.id.editText_user_id);
        editText_note = findViewById(R.id.editText_note);
        button_addUser = findViewById(R.id.button_add_user);
        button_addNote = findViewById(R.id.button_add_note);
        recyclerView_user = findViewById(R.id.recyclerView_user);
        recyclerView_note = findViewById(R.id.recyclerView_note);

    }
}
