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
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);
        final Button TokenchargeButton = (Button)findViewById(R.id.TokenchargeButton);



        final Intent passedIntent = getIntent();

        final Intent kw_Intent = getIntent();
        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
        TextView purchase_kw = (TextView) findViewById(R.id.purchase_kw);
        purchase_kw.setText(kw_data.kw_loggedIn);

        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView purchase_mytoken =(TextView)findViewById(R.id.purchase_mytoken);
        purchase_mytoken.setText(mytoken_data.mytoken_loggedIn);


        final Button tokenpurchaseButton = (Button)findViewById(R.id.tokenpurchaseButton);
        tokenpurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenpurchaseRequest();
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

        TokenchargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Purchase.this, charge_recharge.class);
                startActivity(mainIntent);
            }
        });



        if(AppHelper.RequestQueue == null){
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }




    }
    public void purchase_kwButton_Click(View v) {
        EditText purchaseCount = (EditText) findViewById(R.id.purchaseCount);
        TextView purchase_needtoken = (TextView) findViewById(R.id.purchase_needtoken);
        if (purchaseCount.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), "kW을 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            int n1 = Integer.parseInt(purchaseCount.getText().toString());
            int n2 = 1000;
            purchase_kw = Integer.toString(n1*n2);
            purchase_needtoken.setText(Integer.toString(n1*n2));
        }
    }


    //여기부터 고치기
    public void tokenpurchaseRequest(){
        final String url = "http://210.115.182.155:3000/transfer";
        // 변수 선언


        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");


        final String caller_id = "Wstation"; //뒤에서 붙이기
        final String recipient_id = data.id_loggedIn;
        final String location = "location";
        final String transferAmount = purchase_kw;



        StringRequest mytoken_request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Purchase.this);
                        dialog = builder.setMessage("구매 성공했습니다.").setPositiveButton("확인",null).create();
                        Toast.makeText(getApplicationContext(), "구매 성공했습니다.", Toast.LENGTH_SHORT).show();

                        final Intent passedIntent = getIntent();
                        final Intent kw_Intent = getIntent();
                        final Intent mytokenIntent = getIntent();

                        mytokenInFo mytoken_data = (mytokenInFo)mytokenIntent.getParcelableExtra("mytoken_data");

                        int result_kw = Integer.parseInt(purchase_kw)+ Integer.parseInt(mytoken_data.mytoken_loggedIn);

                        Intent mainIntent = new Intent(getApplicationContext(), Purchase.class);
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
