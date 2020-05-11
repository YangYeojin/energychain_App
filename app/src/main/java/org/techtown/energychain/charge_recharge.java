package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class charge_recharge extends AppCompatActivity {

    String charged_waken_data;
    String recharged_money_data;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_recharge);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);


        final Intent passedIntent = getIntent();
        final Intent kw_Intent = getIntent();
        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView chargerecharge_mywaken =(TextView)findViewById(R.id.chargerecharge_mywaken);
        chargerecharge_mywaken.setText(mytoken_data.mytoken_loggedIn);




        final Button onButton_charge_waken_with_won_really = (Button)findViewById(R.id.onButton_charge_waken_with_won_really);
        onButton_charge_waken_with_won_really.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton_charge_waken_with_won_reallyRequest();
            }
        }); //output


        final Button onButton_recharge_waken_to_won_really = (Button)findViewById(R.id.onButton_recharge_waken_to_won_really);
        onButton_recharge_waken_to_won_really.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton_recharge_waken_to_won_reallyRequest();
            }
        }); //output


        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
                mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                startActivityForResult(mainIntent, 101);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), Purchase.class);
                mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
                mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                startActivityForResult(mainIntent, 101);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(getApplicationContext(), Sale.class);
                mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
                mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                startActivityForResult(mainIntent, 101);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MyPage.class);
                mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
                mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                startActivityForResult(mainIntent, 101);

            }
        });
    }

    public void charge_waken_Click(View v) {
        EditText charge_waken = (EditText) findViewById(R.id.charge_waken);
        TextView charged_waken = (TextView) findViewById(R.id.charged_waken);
        if (charge_waken.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "금액을 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            int n1 = Integer.parseInt(charge_waken.getText().toString());
            int n2 = 1000;
            charged_waken_data = Integer.toString(n1*n2);
            charged_waken.setText(Integer.toString(n1*n2));
        }
    }

    public void charge_money_Click(View v) {
        EditText recharge_waken = (EditText) findViewById(R.id.recharge_waken);
        TextView recharged_money = (TextView) findViewById(R.id.recharged_money);
        if (recharge_waken.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "금액을 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            int n1 = Integer.parseInt(recharge_waken.getText().toString());
            int n2 = 1000;
            recharged_money_data = Integer.toString(n1*n2);
            recharged_money.setText(Integer.toString(n1*n2));
        }
    }



    //여기

    public void onButton_charge_waken_with_won_reallyRequest(){
        final String url = "http://210.115.182.155:3000/transfer";
        // 변수 선언

        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");

        final String caller_id = "Wstation"; //뒤에서 붙이기
        final String recipient_id = data.id_loggedIn;
        final String location = "location";
        final String transferAmount = charged_waken_data;

        StringRequest mytoken_request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(charge_recharge.this);
                        dialog = builder.setMessage("충전 성공했습니다.").setPositiveButton("확인",null).create();
                        Toast.makeText(getApplicationContext(), "충전 성공했습니다.", Toast.LENGTH_SHORT).show();

                        final Intent passedIntent = getIntent();
                        final Intent kw_Intent = getIntent();
                        final Intent mytokenIntent = getIntent();
                        mytokenInFo mytoken_data = (mytokenInFo)mytokenIntent.getParcelableExtra("mytoken_data");

                        int result_kw =  Integer.parseInt(mytoken_data.mytoken_loggedIn) + Integer.parseInt(charged_waken_data);

                        Intent mainIntent = new Intent(getApplicationContext(), charge_recharge.class);
                        mytokenInFo E_mytoken_data = new mytokenInFo(Integer.toString(result_kw));

                        //
                        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");


                        mainIntent.putExtra("data", data);
                        mainIntent.putExtra("kw_data", kw_data);
                        mainIntent.putExtra("mytoken_data", E_mytoken_data);
                        startActivityForResult(mainIntent, 101);

                        //

                        dialog.show();

                        charge_recharge.this.startActivityForResult(mainIntent, 101);
                    }

                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(charge_recharge.this);
                dialog = builder.setMessage("충전 실패했습니다.").setNegativeButton("확인",null).create();
                dialog.show();
                finish();



            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("caller_id", caller_id);
                params.put("recipient_id", recipient_id);
                params.put("location", location);
                params.put("transferAmount", transferAmount);

                return params;
            }
        };
        mytoken_request.setShouldCache(false);
        AppHelper.RequestQueue.add(mytoken_request);
    }


    //여기

    public void onButton_recharge_waken_to_won_reallyRequest(){
        final String url = "http://210.115.182.155:3000/transfer";
        // 변수 선언

        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");

        final String caller_id = data.id_loggedIn; //뒤에서 붙이기
        final String recipient_id = "Wstation";
        final String location = "location";
        final String transferAmount = recharged_money_data;

        StringRequest mytoken_request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(charge_recharge.this);
                        dialog = builder.setMessage("환전 성공했습니다.").setPositiveButton("확인",null).create();
                        Toast.makeText(getApplicationContext(), "환전 성공했습니다.", Toast.LENGTH_SHORT).show();

                        final Intent passedIntent = getIntent();
                        final Intent kw_Intent = getIntent();
                        final Intent mytokenIntent = getIntent();
                        mytokenInFo mytoken_data = (mytokenInFo)mytokenIntent.getParcelableExtra("mytoken_data");

                        int result_kw =  Integer.parseInt(mytoken_data.mytoken_loggedIn) - Integer.parseInt(recharged_money_data);

                        Intent mainIntent = new Intent(getApplicationContext(), charge_recharge.class);
                        mytokenInFo E_mytoken_data = new mytokenInFo(Integer.toString(result_kw));

                        //
                        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");


                        mainIntent.putExtra("data", data);
                        mainIntent.putExtra("kw_data", kw_data);
                        mainIntent.putExtra("mytoken_data", E_mytoken_data);
                        startActivityForResult(mainIntent, 101);

                        //

                        dialog.show();

                        charge_recharge.this.startActivityForResult(mainIntent, 101);
                    }

                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(charge_recharge.this);
                dialog = builder.setMessage("환전 실패했습니다.").setNegativeButton("확인",null).create();
                dialog.show();
                finish();



            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("caller_id", caller_id);
                params.put("recipient_id", recipient_id);
                params.put("location", location);
                params.put("transferAmount", transferAmount);

                return params;
            }
        };
        mytoken_request.setShouldCache(false);
        AppHelper.RequestQueue.add(mytoken_request);
    }




}
