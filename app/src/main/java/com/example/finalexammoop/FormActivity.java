package com.example.finalexammoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FormActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Spinner firstSpinner;
    Spinner secondSpinner;
    String vehicleType;
    String vehicleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setTitle("Bengkel UAS MOOP");

        sessionManager = new SessionManager(FormActivity.this);

        firstSpinner = (Spinner) findViewById(R.id.firstSpinner);
        secondSpinner = (Spinner) findViewById(R.id.secondSpinner);

        firstSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                vehicleType = firstSpinner.getSelectedItem().toString();
                System.out.println(vehicleType);
                String[] items;
                if( vehicleType.equals("Roda Dua") ){
                    items = getResources().getStringArray(R.array.second_spinner_two);
                }else{
                    items = getResources().getStringArray(R.array.second_spinner_four);
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        FormActivity.this, android.R.layout.simple_spinner_item, items);
                secondSpinner.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.logoutButton ){
            sessionManager.logout();
            startActivity(new Intent(FormActivity.this, MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void submitForm(View view){
        vehicleType = firstSpinner.getSelectedItem().toString();
        vehicleName = secondSpinner.getSelectedItem().toString();

        Intent resultIntent = new Intent(FormActivity.this, ResultActivity.class);
        resultIntent.putExtra("vehicle_type",vehicleType);
        resultIntent.putExtra("vehicle_name",vehicleName);
        startActivity(resultIntent);
    }

}