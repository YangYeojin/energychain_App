package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner)findViewById(R.id.regi_bank_data);
        adapter = ArrayAdapter.createFromResource(this, R.array.Org, android.R.layout.simple_spinner_dropdown_item); //R.array.bank로 수정
        spinner.setAdapter(adapter);
    }
}
