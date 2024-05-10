package com.example.LIBM_Assignment_4962;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Branch_M extends AppCompatActivity {

    Button button;
    private DBManager dbManager;

    EditText editText1, editText2, editText3;
    Button addbranch;
    Button clearbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_branch);

        button = findViewById(R.id.btnback5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        dbManager= new DBManager(this);

        dbManager.open();

        addbranch = findViewById(R.id.btnBranchAdd);
        clearbranch =  findViewById(R.id.btnBrachDelete);
        editText1 =  findViewById(R.id.txtBrachId);
        editText2 =  findViewById(R.id.txtBrachName);
        editText3 = findViewById(R.id.txtbranchadd);

        addbranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()||
                        editText3.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Incomplete fields. Please fill in all required information.", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                if(valid) {

                    String BranchID = editText1.getText().toString();
                    String BranchName = editText2.getText().toString();
                    String BranchAddress = editText3.getText().toString();


                    dbManager.insert("insert into Branch values('" + BranchID + "','" + BranchName + "'," +
                            "'" + BranchAddress + "')");
                    Toast.makeText(getApplicationContext(), "Entry successful.", Toast.LENGTH_SHORT).show();
                    Log.e("first","Inserted");
                    dbManager.close();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Error encountered while adding branch.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clearbranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();

                Toast.makeText(getApplicationContext(),
                        "You have successfully deleted the branch.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void adminMenu(){
        Intent intent = new Intent(this, Activity_Admin_Menu.class);
        startActivity(intent);
    }
}
