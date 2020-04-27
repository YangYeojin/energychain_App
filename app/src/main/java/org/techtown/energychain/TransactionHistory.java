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
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        this.InitializeView();
        this.InitializeListener();

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);


        list = (ListView)findViewById(R.id.list);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);

        data.add("판매    2020.04.24  W_Station   200Token");
        data.add("구매    2020.04.24  KB_Bank   200Token");
        data.add("위 예시처럼..여기 받아오기");
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


    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 4, 24);

        dialog.show();
    }



}
