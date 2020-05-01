package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner)findViewById(R.id.regi_bank_data);
        adapter = ArrayAdapter.createFromResource(this, R.array.bank, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //yeojin 0429 edit
        final Button registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        }); //output

        if(AppHelper.RequestQueue == null){
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void sendRequest(){
        final String url = "http://210.115.182.155:3000/SignUp";

        // 변수 선언
        EditText idText = (EditText)findViewById(R.id.idText);
        EditText passwordText = (EditText)findViewById(R.id.passwordText);
        EditText emailText = (EditText)findViewById(R.id.emailText);
        EditText regi_name_date = (EditText)findViewById(R.id.regi_name_date);
        EditText regi_phonenumber_data = (EditText)findViewById(R.id.regi_phonenumber_data);
        EditText regi_number_data = (EditText)findViewById(R.id.regi_number_data);
        Spinner regi_bank_data = (Spinner) findViewById(R.id.regi_bank_data);
        EditText regi_account_data = (EditText)findViewById(R.id.regi_account_data);

        final String idText_String = idText.getText().toString(); //뒤에서 붙이기
        final String passwordText_String = passwordText.getText().toString();
        final String emailText_String = emailText.getText().toString();
        final String regi_name_date_String = regi_name_date.getText().toString();
        final String regi_phonenumber_data_String = regi_phonenumber_data.getText().toString();
        final String regi_number_data_String = regi_number_data.getText().toString();
        final String regi_bank_data_String = regi_bank_data.getSelectedItem().toString();
        final String regi_account_data_String = regi_account_data.getText().toString();

        if(idText_String.equals("")||passwordText_String.equals("")||emailText_String.equals("")||regi_name_date_String.equals("")||regi_phonenumber_data_String.equals("")||regi_number_data_String.equals("")||regi_bank_data_String.equals("")||regi_account_data_String.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("빈칸 없이 입력해주세요.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }

        if(idText_String.contains("@")||idText_String.contains("#")||idText_String.contains("$")||idText_String.contains("%")) { //아이디 특수문자 제한
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("공백 또는 특수문자(@#$%)가 포함된 아이디는 사용할 수 없습니다.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }

        final String idText_String_f = idText_String + "@@"; //id, password 구분문자

        if(regi_phonenumber_data_String.length()!=11) {

            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("전화번호는 총 11자리입니다.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }

        if(regi_number_data_String.length()!=13) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("주민등록번호는 총 13자리입니다.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }

        StringRequest request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("회원 등록에 성공했습니다.").setPositiveButton("확인",null).create();
                    dialog.show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("회원 등록에 실패했습니다.").setNegativeButton("확인",null).create();
                    dialog.show();



                }
            }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", idText_String_f);
                params.put("pw", passwordText_String);
                params.put("name", regi_name_date_String);
                params.put("ph", regi_phonenumber_data_String);
                params.put("email", emailText_String);
                params.put("residentnum", regi_number_data_String);
                params.put("bank", regi_bank_data_String);
                params.put("banknum", regi_account_data_String);
                return params;
            }
        };
        request.setShouldCache(false);
        AppHelper.RequestQueue.add(request);
    }



}
