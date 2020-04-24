package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button NoticeButton = (Button) findViewById(R.id.NoticeButton);
        final Button EditPersonalButton = (Button) findViewById(R.id.EditPersonalButton);
        final Button TransactionHistoryButton = (Button) findViewById(R.id.TransactionHistoryButton);
        final Button ExchangeButton = (Button) findViewById(R.id.ExchangeButton);
        final Button LogoutButton = (Button) findViewById(R.id.LogoutButton);


        NoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, EditMemberInfo.class);
                startActivity(mainIntent);
            }
        });

        EditPersonalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, EditMemberInfo.class);
                startActivity(mainIntent);
            }
        });

        TransactionHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, TransactionHistory.class);
                startActivity(mainIntent);
            }
        });

        ExchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, charge_recharge.class);
                startActivity(mainIntent);
            }
        });

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, charge_recharge.class);
                startActivity(mainIntent);
            }
        });
    }}
