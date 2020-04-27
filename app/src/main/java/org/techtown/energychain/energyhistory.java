package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class energyhistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energyhistory);
        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);


        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(energyhistory.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(energyhistory.this, Purchase.class);
                startActivity(mainIntent);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(energyhistory.this, Sale.class);
                startActivity(mainIntent);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(energyhistory.this, MyPage.class);
                startActivity(mainIntent);
            }
        });
    }
}
