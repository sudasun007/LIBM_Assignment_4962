package com.example.LIBM_Assignment_4962;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Member_Login extends AppCompatActivity {

    private Button buttonBack;
    EditText userName, password;
    Button login;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        buttonBack = findViewById(R.id.btnback2);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
        dbManager = new DBManager(this);
        dbManager.open();

        userName = findViewById(R.id.txtUserName2);
        password = findViewById(R.id.txtpass2);
        login = findViewById(R.id.login2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = dbManager.Select("select * from Member where UserName='" + userName.getText() +
                            "' AND Password='" + password.getText() + "'");

                    if (cursor.moveToNext()) {
                        Toast.makeText(getApplicationContext(), "You are now logged in as Member", Toast.LENGTH_SHORT).show();
                        Intent memberMenuIntent = new Intent(getApplicationContext(), Activity_Member_Menu.class);
                        startActivity(memberMenuIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void backToMainActivity(){
        Intent intent = new Intent(this, Activity_Main.class);
        startActivity(intent);
    }
}
