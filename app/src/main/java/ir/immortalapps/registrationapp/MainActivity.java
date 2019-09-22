package ir.immortalapps.registrationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ir.immortalapps.registrationapp.data.db.User;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;
    private RecyclerView usersRv;
    public static final String EXTRA_KEY_ADD_USER = "add_user";
    private User user;
    private LiveData<List<User>> users;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        if (getIntent().hasExtra(EXTRA_KEY_ADD_USER)){
            user = getIntent().getParcelableExtra(EXTRA_KEY_ADD_USER);

            viewModel.addUser(user);

        }

        setupViews();




    }

    private void setupViews() {

        usersRv = findViewById(R.id.rv_main);
        usersRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersRv.setAdapter(new UserAdapter(users));
                userList = users;
            }
        });

        users = viewModel.getUsers();
        FloatingActionButton fab =  findViewById(R.id.fab_main_addUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddUserActivity.class));
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                User userToDelete = userList.get(viewHolder.getAdapterPosition());
                viewModel.deleteUser(userToDelete);
            }
        }).attachToRecyclerView(usersRv);

    }


}

