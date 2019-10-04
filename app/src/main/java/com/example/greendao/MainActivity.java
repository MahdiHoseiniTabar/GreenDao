package com.example.greendao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greendao.model.Note;
import com.example.greendao.model.Role;
import com.example.greendao.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editText_userName;
    private EditText editText_userId;
    private EditText editText_note;
    private Button button_addUser;
    private Button button_addNote;
    private RadioButton radioButton_ADMIN;
    private RadioButton radioButton_GUEST;
    private RadioButton radioButton_NORMAL;

    private RecyclerView recyclerView_user;
    private RecyclerView recyclerView_note;

    private NoteAdapter noteAdapter;
    private UserAdapter userAdapter;

    private Repository repository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_userName = findViewById(R.id.editText_user_name);
        editText_userId = findViewById(R.id.editText_user_id);
        editText_note = findViewById(R.id.editText_note);
        radioButton_ADMIN = findViewById(R.id.radioButton_admin);
        radioButton_GUEST = findViewById(R.id.radioButton_guest);
        radioButton_NORMAL = findViewById(R.id.radioButton_normal);
        button_addUser = findViewById(R.id.button_add_user);
        button_addNote = findViewById(R.id.button_add_note);
        recyclerView_user = findViewById(R.id.recyclerView_user);
        recyclerView_note = findViewById(R.id.recyclerView_note);
        recyclerView_note.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView_user.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        repository = Repository.getInstance();

        if (userAdapter == null)
            userAdapter = new UserAdapter(this,repository.getUserList());
        recyclerView_user.setAdapter(userAdapter);


        button_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                if (repository.getUser(editText_userName.getText().toString()) != null){
                    Toast.makeText(MainActivity.this, "کاربر تکراری!", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setUserName(editText_userName.getText().toString());
                if (radioButton_ADMIN.isChecked())
                    user.setRole(Role.ADMIN);
                else if(radioButton_GUEST.isChecked())
                    user.setRole(Role.GUEST);
                else
                    user.setRole(Role.NORMAL);

                repository.insertUser(user);

                userAdapter.setUsers(repository.getUserList());
                userAdapter.notifyDataSetChanged();

            }
        });
    }

    class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
        private  Context context;
        private  List<User> users;

        public UserAdapter(Context context, List<User> users) {
            this.context = context;
            this.users = users;
        }
        public void setUsers(List<User> users){
            this.users = users;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
            userViewHolder.bind(users.get(i));
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder{
            private TextView textView_userId;
            private TextView textView_userName;
            public UserViewHolder(@NonNull View itemView) {
                super(itemView);
                textView_userId = itemView.findViewById(R.id.text_user_id);
                textView_userName = itemView.findViewById(R.id.text_user_name);
            }

            public void bind(User user) {
                textView_userName.setText(user.getUserName() );
                textView_userId.setText(user.getId().toString()+"- ");
            }
        }
    }
    class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
        private  Context context;
        private  List<Note> notes;
        public void setNotes(List<Note> notes){
            this.notes = notes;
       }
        public NoteAdapter(Context context, List<Note> notes) {
            this.context = context;
            this.notes = notes;
        }

        @NonNull
        @Override
        public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
            noteViewHolder.bind(notes.get(i));
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        class NoteViewHolder extends RecyclerView.ViewHolder{
            TextView textView_userId;
            TextView textView_note;
            TextView textView_user;
            public NoteViewHolder(@NonNull View itemView) {
                super(itemView);
                textView_userId = itemView.findViewById(R.id.text_user_id);
                textView_note = itemView.findViewById(R.id.text_note);
                textView_user = itemView.findViewById(R.id.text_user);
            }

            public void bind(Note note) {
                textView_userId.setText(note.getUserId().toString());
                textView_user.setText(note.getUser() + "(" + note.getUser().getRole().toString() +")");
                textView_note.setText(note.getText());
            }
        }
    }
}
