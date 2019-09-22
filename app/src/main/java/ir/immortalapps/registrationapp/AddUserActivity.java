package ir.immortalapps.registrationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ir.immortalapps.registrationapp.data.db.User;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        setupViews();
    }

    private void setupViews() {
        final EditText nameEt = findViewById(R.id.et_addUser_name);
        final EditText emailEt = findViewById(R.id.et_addUser_email);
        final EditText countryEt = findViewById(R.id.et_addUser_country);

        Button cancelBtn = findViewById(R.id.btn_addUser_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button addUserBtn = findViewById(R.id.btn_addUser_add);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.EXTRA_KEY_ADD_USER, new User(
                        nameEt.getText().toString(), emailEt.getText().toString(), countryEt.getText().toString())
                );
                startActivity(intent);
            }
        });

    }
}
