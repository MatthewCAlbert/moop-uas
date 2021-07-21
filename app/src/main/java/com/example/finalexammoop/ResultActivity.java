package com.example.finalexammoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView vehicleTypeText;
    TextView vehicleNameText;
    TextView engineResultText;
    TextView gearResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Hasil");

        vehicleTypeText = findViewById(R.id.vehicleType);
        vehicleNameText = findViewById(R.id.vehicleName);
        engineResultText = findViewById(R.id.engineText);
        gearResultText = findViewById(R.id.gearText);

        Intent intent = getIntent();
        String vehicleType = intent.getStringExtra("vehicle_type");
        String vehicleName = intent.getStringExtra("vehicle_name");
        String engineText = "";
        String gearText = "";

        vehicleTypeText.setText(vehicleType);
        vehicleNameText.setText(vehicleName);

        if( vehicleType.equals("Roda Dua") ){
            engineText = getResources().getString(R.string.engine_two);
            if( vehicleName.equals("Sport Bike") )
                gearText = getResources().getString(R.string.gear_bike);
            else
                gearText = getResources().getString(R.string.gear_moped);
        }else{
            engineText = getResources().getString(R.string.engine_four);
            if( vehicleName.equals("SUV") )
                gearText = getResources().getString(R.string.gear_suv);
            else
                gearText = getResources().getString(R.string.gear_sedan);
        }

        engineResultText.setText(engineText);
        gearResultText.setText(gearText);
    }

    public void onClickBack(View view){
        finish();
    }
}