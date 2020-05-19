package org.techtown.energychain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class TransactionHistory extends AppCompatActivity {

    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] mDataset = {"1","2"};
    //1. 정보 가져오기 2.정보 ->어댑터 3.어댑ㅌㅓ ->셋팅

    final Intent passedIntent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
        TextView PrintMemberName = (TextView) findViewById(R.id.PrintMemberName);
        PrintMemberName.setText(data.name_loggedIn);


        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView PrintTokenAmount =(TextView)findViewById(R.id.PrintTokenAmount);
        PrintTokenAmount.setText(mytoken_data.mytoken_loggedIn);



        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);

        final Intent kw_Intent = getIntent();
        final Intent mykw_Intent = getIntent();



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
        gethistory();



    }

    //history 부분
    public void gethistory(){
        final Intent passedIntent = getIntent();
        mInFo data = (mInFo)passedIntent.getParcelableExtra("data");
        String historyurl = "http://210.115.182.155:"+data.orgname_loggedIn+"/historyAPI/"+data.id_loggedIn+"history";

        StringRequest historystringRequest = new StringRequest(Request.Method.GET, historyurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String history_response) {
                try{
                    String response_dummy = history_response.replaceAll("\\\\", "");
                    String response_dummy2 = response_dummy.substring(1, response_dummy.length()-1);
                    response_dummy = response_dummy2.replaceAll(" \\{", ",");
                    response_dummy2 = response_dummy.replaceAll("\\[", "\\[\\{");
                    response_dummy = response_dummy2.replaceAll("seconds", "seconds\"");
                    response_dummy2 = response_dummy.replaceAll(" nanos", ",\"nanos\"");
                    response_dummy = response_dummy2.replaceAll("\\}\"", "\\}");
                    response_dummy2 = response_dummy.replaceAll(",\"seconds", ",\\{\"seconds");
                    history_response = "{history:"+response_dummy2+"}";

                    JSONObject jsonObj = new JSONObject(history_response);
                    JSONArray arrayhistory = jsonObj.getJSONArray("history");
                    List<historyData> history=new ArrayList<>();

                    for(int i =0, j=arrayhistory.length();i<j;i++){
                        JSONObject obj = arrayhistory.getJSONObject(i);


                        historyData history_data = new historyData();
                        history_data.setCellerid(obj.getString("callerid"));
                        history_data.setLocation(obj.getString("location"));
                        history_data.setRecipientid(obj.getString("recipientid"));
                        history_data.setTransferAmount(obj.getInt("transferAmount"));
                        history_data.setSecond(obj.getString("seconds"));
                        history.add(history_data);
                    }


                    // specify an adapter (see also next example)
                    mAdapter = new MyAdapter(history);
                    recyclerView.setAdapter(mAdapter);


                }catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        if(AppHelper.RequestQueue == null){
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }


        historystringRequest.setShouldCache(false);
        AppHelper.RequestQueue.add(historystringRequest);
    }









}
