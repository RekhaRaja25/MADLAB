package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.database.Cursor;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editId, editName, editLocation, editDesignation;
    Button btnInsert, btnView, btnUpdate, btnDelete;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHelper(this);

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editLocation = findViewById(R.id.editLocation);
        editDesignation = findViewById(R.id.editDesignation);

        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        listView = findViewById(R.id.listView);

        // INSERT
        btnInsert.setOnClickListener(v -> {
            boolean inserted = myDb.insertData(
                    editName.getText().toString(),
                    editLocation.getText().toString(),
                    editDesignation.getText().toString()
            );

            Toast.makeText(this, inserted ? "Inserted" : "Failed", Toast.LENGTH_SHORT).show();
        });

        // VIEW (ListView Display)
        btnView.setOnClickListener(v -> {
            Cursor res = myDb.getData();

            if (res.getCount() == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<String> list = new ArrayList<>();

            while (res.moveToNext()) {
                list.add(
                        "ID: " + res.getString(0) + "\n" +
                                "Name: " + res.getString(1) + "\n" +
                                "Location: " + res.getString(2) + "\n" +
                                "Designation: " + res.getString(3)
                );
            }

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

            listView.setAdapter(adapter);

            Toast.makeText(this, "Total Records: " + res.getCount(), Toast.LENGTH_SHORT).show();
        });

        // UPDATE
        btnUpdate.setOnClickListener(v -> {
            boolean updated = myDb.updateData(
                    editId.getText().toString(),
                    editName.getText().toString(),
                    editLocation.getText().toString(),
                    editDesignation.getText().toString()
            );

            Toast.makeText(this, updated ? "Updated" : "Failed", Toast.LENGTH_SHORT).show();
        });

        // DELETE
        btnDelete.setOnClickListener(v -> {
            int rows = myDb.deleteData(editId.getText().toString());

            Toast.makeText(this, rows > 0 ? "Deleted" : "Not Found", Toast.LENGTH_SHORT).show();
        });
    }
}
