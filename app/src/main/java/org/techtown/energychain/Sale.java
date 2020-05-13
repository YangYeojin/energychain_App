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
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Sale extends AppCompatActivity {

    String sale_kw;
    String sale_token;
    private AlertDialog dialog;
    String KW_Cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);

        final Intent passedIntent = getIntent();

        final Intent kw_Intent = getIntent();
        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
        TextView sale_kw = (TextView) findViewById(R.id.sale_kw);
        sale_kw.setText(kw_data.kw_loggedIn);
        KW_Cost=kw_data.kw_loggedIn;

        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView sale_mytoken =(TextView)findViewById(R.id.sale_mytoken);
        sale_mytoken.setText(mytoken_data.mytoken_loggedIn);

        final Intent mykw_Intent = getIntent();
        mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");
        TextView sale_mykw = (TextView) findViewById(R.id.sale_mykw);
        sale_mykw.setText(mykw_data.mykw_loggedIn);



        final Button tokensaleButton = (Button)findViewById(R.id.tokensaleButton);
        tokensaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokensaleRequest();
            }
        }); //output

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


        if(AppHelper.RequestQueue == null){
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }
    public void sale_kwButton_Click(View v) {
        EditText saleCount = (EditText) findViewById(R.id.saleCount);
        TextView profitForSale = (TextView) findViewById(R.id.profitForSale);
        if (saleCount.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "필요한 kW을 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            int n1 = Integer.parseInt(saleCount.getText().toString());
            int n2 = Integer.parseInt(KW_Cost);
            sale_token = Integer.toString(n1*n2);
            sale_kw = Integer.toString(n1);
            profitForSale.setText(Integer.toString(n1*n2));
        }
    }


    public void tokensaleRequest(){
        final String url = "http://210.115.182.155:3000/transfer";
        // 변수 선언


        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");


        final String caller_id = "Wstation"; //뒤에서 붙이기
        final String recipient_id = data.id_loggedIn;
        final String location = "location";
        final String transferAmount = sale_kw;


        StringRequest mytoken_request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Sale.this);
                        dialog = builder.setMessage("판매 성공했습니다.").setPositiveButton("확인",null).create();
                        Toast.makeText(getApplicationContext(), "판매 성공했습니다.", Toast.LENGTH_SHORT).show();

                        final Intent passedIntent = getIntent();
                        final Intent kw_Intent = getIntent();
                        final Intent mytokenIntent = getIntent();
                        final Intent mykw_Intent = getIntent();

                        mytokenInFo mytoken_data = (mytokenInFo)mytokenIntent.getParcelableExtra("mytoken_data");
                        mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");

                        int result_token = Integer.parseInt(mytoken_data.mytoken_loggedIn) + Integer.parseInt(sale_token);
                        Float result_kw = Float.parseFloat(mykw_data.mykw_loggedIn) -  Float.parseFloat(sale_kw); //여기

                        Intent mainIntent = new Intent(getApplicationContext(), Sale.class);
                        mytokenInFo E_mytoken_data = new mytokenInFo(Integer.toString(result_token));
                        mykwInFo E_mykw_data = new mykwInFo(Float.toString(result_kw));
                        //
                        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
                        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");


                        mainIntent.putExtra("data", data);
                        mainIntent.putExtra("kw_data", kw_data);
                        mainIntent.putExtra("mytoken_data", E_mytoken_data);
                        mainIntent.putExtra("mykw_data", E_mykw_data);
                        startActivityForResult(mainIntent, 101);

                        //

                        dialog.show();

                        Sale.this.startActivityForResult(mainIntent, 101);
                    }

                }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Sale.this);
                dialog = builder.setMessage("판매 실패했습니다.").setNegativeButton("확인",null).create();
                Toast.makeText(getApplicationContext(), "판매 실패했습니다.", Toast.LENGTH_LONG).show();
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
