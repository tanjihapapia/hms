package com.example.hms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorActivity extends AppCompatActivity {

    private String[] doctorNames = {
            "Dr. Smith", "Dr. Johnson", "Dr. Williams", "Dr. Brown", "Dr. Davis",
            "Dr. Miller", "Dr. Wilson", "Dr. Moore", "Dr. Taylor", "Dr. Anderson",
            "Dr. Thomas", "Dr. Jackson", "Dr. White", "Dr. Harris", "Dr. Martin",
            "Dr. Thompson", "Dr. Garcia", "Dr. Martinez", "Dr. Robinson", "Dr. Clark",
            "Dr. Rodriguez", "Dr. Lewis", "Dr. Lee", "Dr. Walker", "Dr. Hall",
            "Dr. Allen", "Dr. Young", "Dr. Hernandez", "Dr. King", "Dr. Wright"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        ListView listView = findViewById(R.id.doctorList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, doctorNames);

        listView.setAdapter(adapter);
    }
}