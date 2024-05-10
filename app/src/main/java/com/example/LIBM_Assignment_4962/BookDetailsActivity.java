package com.example.LIBM_Assignment_4962;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class BookDetailsActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private TextView textViewTitle;
    private ListView listViewBookDetails;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        dbHelper = new DBHelper(this);

        textViewTitle = findViewById(R.id.textViewTitle);
        listViewBookDetails = findViewById(R.id.listViewBookDetails);

        // Get the book ID passed from the previous activity
        String bookId = getIntent().getStringExtra("BOOK_ID");

        // Ensure bookId is not null before fetching book details
        if (bookId != null) {
            // Retrieve book details from the database using DBHelper
            Cursor cursor = dbHelper.getBookDetails(bookId);
            ArrayList<String> bookDetailsList = new ArrayList<>();

            if (cursor != null && cursor.moveToFirst()) {
                // Check if column indices are valid
                int columnIndexBookName = cursor.getColumnIndex(DBHelper.coloum2);
                int columnIndexBookPublisher = cursor.getColumnIndex(DBHelper.coloum3);
                int columnIndexBookAuthor = cursor.getColumnIndex(DBHelper.coloum4);
                int columnIndexBranch = cursor.getColumnIndex(DBHelper.coloum5);

                if (columnIndexBookName != -1 && columnIndexBookPublisher != -1 &&
                        columnIndexBookAuthor != -1 && columnIndexBranch != -1) {
                    // Extract book details from the cursor
                    String bookName = cursor.getString(columnIndexBookName);
                    String bookPublisher = cursor.getString(columnIndexBookPublisher);
                    String bookAuthor = cursor.getString(columnIndexBookAuthor);
                    String branch = cursor.getString(columnIndexBranch);

                    // Add book details to the list
                    bookDetailsList.add("Book Name: " + bookName);
                    bookDetailsList.add("Book Publisher: " + bookPublisher);
                    bookDetailsList.add("Book Author: " + bookAuthor);
                    bookDetailsList.add("Branch: " + branch);
                }

                cursor.close();
            }

            // Set the title of the activity
            textViewTitle.setText("Book Details");

            // Set up the list view with book details
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookDetailsList);
            listViewBookDetails.setAdapter(adapter);
        } else {
            // Handle the case where bookId is null (e.g., display an error message)
            Toast.makeText(this, "Book ID is null", Toast.LENGTH_SHORT).show();
        }
    }
}
