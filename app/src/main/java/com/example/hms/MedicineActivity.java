package com.example.hms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MedicineActivity extends AppCompatActivity {

    private String[] medicineNames = {
            "Acetaminophen",
            "Ibuprofen",
            "Aspirin",
            "Lisinopril",
            "Levothyroxine",
            "Amlodipine",
            "Metformin",
            "Atorvastatin",
            "Simvastatin",
            "Metoprolol",
            "Omeprazole",
            "Losartan",
            "Albuterol",
            "Azithromycin",
            "Amoxicillin",
            "Prednisone",
            "Citalopram",
            "Escitalopram",
            "Sertraline",
            "Fluoxetine",
            "Hydrochlorothiazide",
            "Gabapentin",
            "Tramadol",
            "Warfarin",
            "Ranitidine",
            "Pantoprazole",
            "Doxycycline",
            "Clindamycin",
            "Cephalexin",
            "Amphetamine",
            "Diazepam",
            "Oxycodone",
            "Morphine",
            "Furosemide",
            "Metronidazole",
            "Bupropion",
            "Lorazepam",
            "Venlafaxine",
            "Trazodone",
            "Phenytoin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        ListView listView = findViewById(R.id.medicine);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, medicineNames);

        listView.setAdapter(adapter);
    }
}