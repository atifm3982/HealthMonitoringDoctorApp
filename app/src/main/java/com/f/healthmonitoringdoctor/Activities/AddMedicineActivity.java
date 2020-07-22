package com.f.healthmonitoringdoctor.Activities;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.f.healthmonitoringdoctor.R;
import com.google.gson.Gson;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AddMedicineActivity extends AppCompatActivity {
Button save;
EditText medicinename,disease,symptom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        medicinename = findViewById(R.id.medicine_name);
        disease = findViewById(R.id.disease);
        symptom = findViewById(R.id.symptom);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (medicinename.getText().toString().isEmpty()) {
                    medicinename.setError("Enter Name");
                } else if (disease.getText().toString().isEmpty()) {
                    disease.setError("Enter disease");
                } else if (symptom.getText().toString().isEmpty()) {
                    symptom.setError("Enter symptom");
                } else {




                }

            }
        });
    }


    }


