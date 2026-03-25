package com.example.eb;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    EditText unitsInput, phoneInput;
    TextView result;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitsInput = findViewById(R.id.units);
        phoneInput = findViewById(R.id.phone);
        result = findViewById(R.id.result);
        btnCalculate = findViewById(R.id.btnCalculate);

        // Request SMS permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        btnCalculate.setOnClickListener(v -> calculateBill());
    }

    private void calculateBill() {

        String unitsStr = unitsInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();

        if (unitsStr.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int units = Integer.parseInt(unitsStr);
        double bill;

        // Electricity tariff logic
        if (units <= 100) {
            bill = units * 1.5;
        } else if (units <= 200) {
            bill = 100 * 1.5 + (units - 100) * 2;
        } else {
            bill = 100 * 1.5 + 100 * 2 + (units - 200) * 3;
        }

        String message = "Electricity Bill = ₹ " + bill;

        // Show result
        result.setText(message);

        // Show Alert Dialog
        new AlertDialog.Builder(this)
                .setTitle("Bill Generated")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();

        // Show Toast
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Send SMS
        sendSMS(phone, message);
    }

    private void sendSMS(String phone, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
