package com.example.day10;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    TextView tvTipeMobil, cityPriceTextView, tvRent, tvDiscount, tvTotal;
    int days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTipeMobil = findViewById(R.id.tvTipeMobil);
        cityPriceTextView = findViewById(R.id.tvCity); // Ubah tvCity menjadi cityPriceTextView
        tvRent = findViewById(R.id.tvRent);
        tvDiscount = findViewById(R.id.tvDiscount);
        tvTotal = findViewById(R.id.tvTotal);

        String carType = getIntent().getStringExtra("carType");
        String city = getIntent().getStringExtra("city");
        days = getIntent().getIntExtra("days", 1);

        int rentPrice = getRentPrice(carType);
        int cityPrice = getCityPrice(city);
        int totalRent = rentPrice * days;


        double discount = calculateDiscount(totalRent);
        double totalPrice = totalRent - discount;

        DecimalFormat formatter = new DecimalFormat("#,###");

        tvTipeMobil.setText("Car Type: " + carType + " (" + formatter.format(rentPrice) + ")");
        cityPriceTextView.setText("City Price Rp. " + formatter.format(cityPrice)); // Mengganti tvCity menjadi cityPriceTextView
        tvRent.setText("Day Of Rent: " + formatter.format(days) + " days");
        tvDiscount.setText("Discount Rp. " + formatter.format(discount));
        tvTotal.setText("Total Rp. " + formatter.format(totalPrice));
    }

    private int getRentPrice(String carType) {
        switch (carType) {
            case "Pajero":
                return 2400000;
            case "Alphard":
                return 1550000;
            case "Inova":
                return 850000;
            case "Brio":
                return 550000;
            default:
                return 0;
        }
    }

    private int getCityPrice(String city) {
        return city.equals("Inside City") ? 135000 : 250000;
    }

    private double calculateDiscount(int totalRent) {
        double discount = 0;
        if (totalRent > 10000000) {
            discount = totalRent * 0.07;
        } else if (totalRent > 5000000) {
            discount = totalRent * 0.05;
        }
        return discount;
    }
}
