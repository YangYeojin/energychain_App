package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TransactionHistory extends AppCompatActivity {

    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private ListView historylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
        TextView PrintMemberName = (TextView) findViewById(R.id.PrintMemberName);
        PrintMemberName.setText(data.name_loggedIn);


        final Intent kw_Intent = getIntent();


        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView PrintTokenAmount =(TextView)findViewById(R.id.PrintTokenAmount);
        PrintTokenAmount.setText(mytoken_data.mytoken_loggedIn);



        //this.InitializeView();
        //this.InitializeListener();

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);


        historylist = (ListView)findViewById(R.id.historylist);

        List<String> historydata = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,historydata);
        historylist.setAdapter(adapter);

        historydata.add("판매    2020.04.24  W_Station   200Token");
        historydata.add("구매    2020.04.24  KB_Bank   200Token"); //공부하기
        historydata.add("위 예시처럼..여기 받아오기");
        adapter.notifyDataSetChanged();



        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(TransactionHistory.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(TransactionHistory.this, Purchase.class);
                startActivity(mainIntent);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(TransactionHistory.this, Sale.class);
                startActivity(mainIntent);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(TransactionHistory.this, MyPage.class);
                startActivity(mainIntent);
            }
        });

    }







}
