package com.example.hms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout appointmentLayout = findViewById(R.id.Lab);
        LinearLayout doctorLayout = findViewById(R.id.Medicine);
        LinearLayout medicineLayout = findViewById(R.id.doctor);
        LinearLayout staffLayout = findViewById(R.id.Articales);
        LinearLayout labLayout = findViewById(R.id.order);
        Button logoutButton = findViewById(R.id.logoutButton);

        appointmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AppointmentActivity.class));
            }
        });

        doctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, DoctorActivity.class));
            }
        });

        medicineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MedicineActivity.class));
            }
        });

        staffLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, StaffActivity.class));
            }
        });

        labLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, LabActivity.class));
            }
        });

       /* logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                finish();
            }
        });*/
    }
}