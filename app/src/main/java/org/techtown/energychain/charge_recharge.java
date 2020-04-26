package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class charge_recharge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_recharge);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);


        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(charge_recharge.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(charge_recharge.this, Purchase.class);
                startActivity(mainIntent);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(charge_recharge.this, Sale.class);
                startActivity(mainIntent);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(charge_recharge.this, MyPage.class);
                startActivity(mainIntent);

            }
        });
    }

    public void charge_waken_Click(View v) {
        EditText charge_waken = (EditText) findViewById(R.id.charge_waken);
        TextView charged_waken = (TextView) findViewById(R.id.charged_waken);
        int n1 = Integer.parseInt(charge_waken.getText().toString());
        int n2 = 1000;
        charged_waken.setText(Integer.toString(n1*n2));
        charge_waken.setText("");
    }

    public void charge_money_Click(View v) {
        EditText recharge_waken = (EditText) findViewById(R.id.recharge_waken);
        TextView recharged_money = (TextView) findViewById(R.id.recharged_money);
        int n1 = Integer.parseInt(recharge_waken.getText().toString());
        recharged_money.setText(Integer.toString(n1));
        recharge_waken.setText("");
    }
}
