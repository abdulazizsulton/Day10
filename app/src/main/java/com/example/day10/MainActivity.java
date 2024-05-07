package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroupCarType, radioGroupCity;
    Button btnRent;
    String carType, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupCarType = findViewById(R.id.btnRadiogroupp);
        radioGroupCity = findViewById(R.id.btnRadiogrouppp);
        btnRent = findViewById(R.id.btnRent);

        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarType = radioGroupCarType.getCheckedRadioButtonId();
                int selectedCity = radioGroupCity.getCheckedRadioButtonId();

                if (selectedCarType == -1 || selectedCity == -1) {
                    Toast.makeText(MainActivity.this, "Please select car type and city", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton carTypeRadioButton = findViewById(selectedCarType);
                carType = carTypeRadioButton.getText().toString();

                RadioButton cityRadioButton = findViewById(selectedCity);
                city = cityRadioButton.getText().toString();

                // Ambil nilai dari EditText etTotalDay
                EditText etKodeBarang = findViewById(R.id.etKodebarang);
                String totalDayString = etKodeBarang.getText().toString().trim();
                int days = 0;
                if (!totalDayString.isEmpty()) {
                    days = Integer.parseInt(totalDayString);
                }

                // Masukkan nilai days ke dalam intent
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("carType", carType);
                intent.putExtra("city", city);
                intent.putExtra("days", days);
                startActivity(intent);
            }
        });
    }
}