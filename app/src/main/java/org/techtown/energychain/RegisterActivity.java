package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private String userORG;
    private AlertDialog dialog;
    int id_check_OK= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner)findViewById(R.id.regi_bank_data);
        adapter = ArrayAdapter.createFromResource(this, R.array.bank, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //소속
        RadioGroup OrgGroup = (RadioGroup)findViewById(R.id.OrgGroup);
        int OrgGroupID = OrgGroup.getCheckedRadioButtonId();
        userORG= ((RadioButton)findViewById(OrgGroupID)).getText().toString();

        OrgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton OrgButton = (RadioButton)findViewById(checkedId);
                userORG = OrgButton.getText().toString();

            }
        });

        final Button validataButton =(Button)findViewById(R.id.validateButton);
        validataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idcheckRequest();
            }
        });

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

    //중복체크
    public void idcheckRequest() {
        EditText idText = (EditText) findViewById(R.id.idText);
        final String idText_String = idText.getText().toString();
        final String checkURL = "http://210.115.182.155:3000/balanceOf/" + idText_String;

        if (idText_String.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다.").setPositiveButton("확인", null).create();
            dialog.show();
            return;
        }

        if (idText_String.contains("@") || idText_String.contains("#") || idText_String.contains("$") || idText_String.contains("%")) { //아이디 특수문자 제한
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("공백 또는 특수문자(@#$%)가 포함된 아이디는 사용할 수 없습니다.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }

        //여기부터
        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String response_dummy = response.replaceAll("\\\\", "");
                response = response_dummy.substring(1, response_dummy.length()-1);

                if (!(response.equals(""))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("중복된 아이디입니다. 다른 아이디를 입력해주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }else{
                    id_check_OK=1;
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("사용가능한 아이디입니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    //수정못하게하는 알고리즘
                }
                //response가 "0"이면 데이터 있음 -> dialog로 메시지 출력후 다시 register로 가져오기
                // 중복체크 했는지 따지고 회원가입되도록 하기
                //만약 id값 체크 완료 되었으면 못바꾸도록 하기

             }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        if(AppHelper.RequestQueue == null){
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }


        stringRequest.setShouldCache(false);
        AppHelper.RequestQueue.add(stringRequest);
    }


            //여기까지



    //회원가입
    public void sendRequest(){

        final String url = "http://210.115.182.155:3000/SignUp";
        // 변수 선언
        EditText idText = (EditText)findViewById(R.id.idText);
        EditText passwordText = (EditText)findViewById(R.id.passwordText);
        EditText emailText = (EditText)findViewById(R.id.emailText);
        EditText regi_name_date = (EditText)findViewById(R.id.regi_name_date);
        EditText regi_phonenumber_data = (EditText)findViewById(R.id.regi_phonenumber_data);
        EditText regi_number_data = (EditText)findViewById(R.id.regi_number_data);
        EditText regi_car_data = (EditText)findViewById(R.id.regi_car_data);
        Spinner regi_bank_data = (Spinner) findViewById(R.id.regi_bank_data);
        EditText regi_account_data = (EditText)findViewById(R.id.regi_account_data);

        final String idText_String = idText.getText().toString(); //뒤에서 붙이기
        final String passwordText_String = passwordText.getText().toString();
        final String emailText_String = emailText.getText().toString();
        final String regi_name_date_String = regi_name_date.getText().toString();
        final String regi_phonenumber_data_String = regi_phonenumber_data.getText().toString();
        final String regi_number_data_String = regi_number_data.getText().toString();
        final String regi_car_data_String = regi_car_data.getText().toString(); // 수정
        final String regi_bank_data_String = regi_bank_data.getSelectedItem().toString();
        final String regi_account_data_String = regi_account_data.getText().toString();
        switch (userORG){
            case "전기차":
                userORG = "3000";
                break;
            case "산업단지":
                userORG = "4000";
                break;
            case "태양열":
                userORG = "5000";
                break;
        }
        final String regi_userorg_String = userORG;

        if(idText_String.equals("")||passwordText_String.equals("")||emailText_String.equals("")||regi_name_date_String.equals("")||regi_phonenumber_data_String.equals("")||regi_number_data_String.equals("")||regi_car_data_String.equals("")||regi_bank_data_String.equals("")||regi_account_data_String.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("빈칸 없이 입력해주세요.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }


        final String idText_String_f = idText_String; //id, password 구분문자


        if (idText_String.contains("@") || idText_String.contains("#") || idText_String.contains("$") || idText_String.contains("%")) { //아이디 특수문자 제한
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("공백 또는 특수문자(@#$%)가 포함된 아이디는 사용할 수 없습니다.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }


        if(id_check_OK != 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            dialog = builder.setMessage("아이디 중복체크를 해주세요.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }


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
                    }

                    }, new Response.ErrorListener() {



                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("회원 등록에 실패했습니다.").setNegativeButton("확인",null).create();
                    dialog.show();
                    finish();

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
                params.put("carnum", regi_car_data_String);
                params.put("orgname", regi_userorg_String);
                return params;
            }
        };
        request.setShouldCache(false);
        AppHelper.RequestQueue.add(request);
    }



}
