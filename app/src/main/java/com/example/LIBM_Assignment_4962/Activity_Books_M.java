package com.example.LIBM_Assignment_4962;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Books_M extends AppCompatActivity {

    Button button;
    private DBManager dbManager;

    EditText editText1, editText2, editText3,editText4,editText5;
    Button addbook;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);

        button = findViewById(R.id.btnback3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        dbManager= new DBManager(this);

        dbManager.open();

        addbook = findViewById(R.id.btnbookadd);
        clear =  findViewById(R.id.btndelete);
        editText1 =  findViewById(R.id.txtBookID);
        editText2 =  findViewById(R.id.txtBooktitle);
        editText3 = findViewById(R.id.txtpublishername);
        editText4 = findViewById(R.id.txtauthorname2);
        editText5 = findViewById(R.id.txtaddbranchname);

        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()||
                        editText3.getText().toString().isEmpty()||editText4.getText().toString().isEmpty()||
                        editText5.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show();
                    valid = false;
                }
                if(valid) {

                    String BookID = editText1.getText().toString();
                    String BookName = editText2.getText().toString();
                    String BookPublisher = editText3.getText().toString();
                    String BookAuthor = editText4.getText().toString();
                    String Branch = editText5.getText().toString();


                    dbManager.insert("insert into Book values('" + BookID + "','" + BookName + "'," +
                            "'" + BookPublisher + "','" + BookAuthor + "','" + Branch + "')");
                    Toast.makeText(getApplicationContext(), "Book successfully added.", Toast.LENGTH_SHORT).show();
                    Log.e("first","Inserted");
                    dbManager.close();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "There was an error adding the book.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1.getText().clear();
                editText2.getText().clear();
                editText3.getText().clear();
                editText4.getText().clear();
                editText5.getText().clear();

                Toast.makeText(getApplicationContext(),
                        "Your book has been successfully deleted.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void adminMenu(){
        Intent intent = new Intent(this, Activity_Admin_Menu.class);
        startActivity(intent);
    }
    public void onViewDetailsClicked(View view) {
        // Start the details activity or fragment
        Intent intent = new Intent(this, BookDetailsActivity.class);
        startActivity(intent);
    }

}
