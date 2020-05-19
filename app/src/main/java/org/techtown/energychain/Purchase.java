package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Purchase extends AppCompatActivity {


    String purchase_kw;
    String purchase_token;
    private AlertDialog dialog;
    String KW_Cost;
    int pu_check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);
        final Button TokenchargeButton = (Button)findViewById(R.id.TokenchargeButton);



        //intent 시작
        final Intent passedIntent = getIntent();

        final Intent kw_Intent = getIntent();
        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
        TextView purchase_kw = (TextView) findViewById(R.id.purchase_kw);
        purchase_kw.setText(kw_data.kw_loggedIn);
        KW_Cost=kw_data.kw_loggedIn;

        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView purchase_mytoken =(TextView)findViewById(R.id.purchase_mytoken);
        purchase_mytoken.setText(mytoken_data.mytoken_loggedIn);

        final Intent mykw_Intent = getIntent();
        mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");
        TextView purchase_mykw = (TextView) findViewById(R.id.purchase_mykw);
        purchase_mykw.setText(mykw_data.mykw_loggedIn);


        final Button tokenpurchaseButton = (Button)findViewById(R.id.tokenpurchaseButton);
        tokenpurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenpurchaseRequest();
            }
        });

        //상단메뉴 버튼 구현
        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
                mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
                mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                mainIntent.putExtra("mykw_data", mykw_data);
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
                mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                mainIntent.putExtra("mykw_data", mykw_data);
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
                mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                mainIntent.putExtra("mykw_data", mykw_data);
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
                mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                mainIntent.putExtra("mykw_data", mykw_data);
                startActivityForResult(mainIntent, 101);
            }
        });

        TokenchargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Purchase.this, charge_recharge.class);
                mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
                mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
                mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                mainIntent.putExtra("data", data);
                mainIntent.putExtra("kw_data", kw_data);
                mainIntent.putExtra("mytoken_data", mytoken_data);
                mainIntent.putExtra("mykw_data", mykw_data);
                startActivityForResult(mainIntent, 101);
            }
        });



        if(AppHelper.RequestQueue == null){
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }




    }
    //구매버튼 - (금액구하기)
    public void purchase_kwButton_Click(View v) {
        EditText purchaseCount = (EditText) findViewById(R.id.purchaseCount);
        TextView purchase_needtoken = (TextView) findViewById(R.id.purchase_needtoken);


        if (purchaseCount.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "구매할 kW을 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            int n1 = Integer.parseInt(purchaseCount.getText().toString());
            int n2 = Integer.parseInt(KW_Cost);
            purchase_token = Integer.toString(n1*n2);
            purchase_kw = Integer.toString(n1);
            purchase_needtoken.setText(Integer.toString(n1*n2));
            pu_check=1;
        }
    }


    //구매버튼 - (거래기능)
    public void tokenpurchaseRequest(){

        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");

        final String url = "http://210.115.182.155:"+data.orgname_loggedIn+"/transfer";

        final String caller_id = data.id_loggedIn;
        final String recipient_id = "Wstation";
        final String location = "location";
        final String transferAmount = purchase_token;

        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");


        if(pu_check!=1){
            AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
            dialog = builder.setMessage("구매할 kW을 입력하세요.").setPositiveButton("확인", null).create();
            dialog.show();
            return;
        }

        if(Integer.parseInt(purchase_token)>Integer.parseInt(mytoken_data.mytoken_loggedIn)){
            AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
            dialog = builder.setMessage("보유 토큰이 부족합니다.").setPositiveButton("확인", null).create();
            dialog.show();
            return;
        }


        StringRequest mytoken_request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final Intent passedIntent = getIntent();
                        final Intent kw_Intent = getIntent();
                        final Intent mytokenIntent = getIntent();
                        final Intent mykw_Intent = getIntent();


                        AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
                        dialog = builder.setMessage("구매 성공했습니다.").setPositiveButton("확인",null).create();

                        mytokenInFo mytoken_data = (mytokenInFo)mytokenIntent.getParcelableExtra("mytoken_data");
                        mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                        int result_token =  Integer.parseInt(mytoken_data.mytoken_loggedIn) - Integer.parseInt(purchase_token);
                        Float result_kw =  Float.parseFloat(mykw_data.mykw_loggedIn) +  Float.parseFloat(purchase_kw); //여기

                        Intent mainIntent = new Intent(getApplicationContext(), Purchase.class);
                        mytokenInFo E_mytoken_data = new mytokenInFo(Integer.toString(result_token));
                        mykwInFo E_mykw_data = new mykwInFo(Float.toString(result_kw));

                        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");


                        mainIntent.putExtra("data", data);
                        mainIntent.putExtra("kw_data", kw_data);
                        mainIntent.putExtra("mytoken_data", E_mytoken_data);
                        mainIntent.putExtra("mykw_data", E_mykw_data);

                        startActivityForResult(mainIntent, 101);



                        dialog.show();

                        Purchase.this.startActivityForResult(mainIntent, 101);

                    }

                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
                dialog = builder.setMessage("구매 실패했습니다.").setNegativeButton("확인",null).create();
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
