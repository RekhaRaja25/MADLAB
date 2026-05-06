package com.example.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Called when app starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Link menu XML file
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_new) {
            Toast.makeText(MainActivity.this, "New Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.menu_open) {
            Toast.makeText(MainActivity.this, "Open Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.menu_close) {
            Toast.makeText(MainActivity.this, "Close Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
