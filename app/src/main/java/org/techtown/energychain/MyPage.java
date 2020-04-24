package org.techtown.energychain;


import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;


public class MyPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        final Button NoticeButton = (Button)findViewById(R.id.NoticeButton);
        final Button EditPersonalButton = (Button)findViewById(R.id.EditPersonalButton);
        final Button TransactionHistoryButton = (Button)findViewById(R.id.TransactionHistoryButton);
        final Button ExchangeButton = (Button)findViewById(R.id.ExchangeButton);
        //final Button LogoutButton = (Button)findViewById(R.id.LogoutButton);
        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);


        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, Purchase.class);
                startActivity(mainIntent);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MyPage.this, Sale.class);
                startActivity(mainIntent);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, MyPage.class);
                startActivity(mainIntent);
            }
        });


        NoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, charge_recharge.class); //공지사항
                startActivity(mainIntent);
            }});

        EditPersonalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, EditMemberInfoActivity.class); // 개인정보수정
                startActivity(mainIntent);
            }});

        TransactionHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, energyhistory.class); //거래내역
                startActivity(mainIntent);
            }});

        ExchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MyPage.this, charge_recharge.class);
                startActivity(mainIntent);
            }});

    }
}