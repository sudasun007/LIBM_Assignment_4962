package com.example.LIBM_Assignment_4962;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Admin_Login extends AppCompatActivity {

    private Button button;
    EditText UserName, Password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        button = findViewById(R.id.btnback1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        UserName = findViewById(R.id.txtUserName1);
        Password = findViewById(R.id.txtpass1);
        login = findViewById(R.id.login1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserName.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (UserName.getText().toString().equals("Sudheera007") && Password.getText().toString().equals("SudLIBM")) {
                        Toast.makeText(getApplicationContext(), "You are now logged in as Admin.", Toast.LENGTH_SHORT).show();
                        Intent libAdmin = new Intent(getApplicationContext(), Activity_Admin_Menu.class);
                        startActivity(libAdmin);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


    }

    public void MainActivity(){
        Intent intent = new Intent(this, Activity_Main.class);
        startActivity(intent);
    }
}
