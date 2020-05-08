package org.techtown.energychain;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class LoginActivity extends AppCompatActivity{

    String id_server, pw_server, name_server, ph_server, email_server, residentnum_server, bank_server, banknum_server, carnum_server;
    String kw_server;

    // # 05.02
    // 로그인 실패를 알리기 위한 창을 위해 AlterDialog 선언
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerButton = (TextView)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                //LoginActivity.this.startActivity(registerIntent);
            }
        });



        // yeojin start
        // * 로그인 버튼 선언
        // 참고 : 나는 통일감있게 loginButton을 TextView로 변경함
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        // * 여기서부터 로그인 수행 : loginButton TextView를 클릭하면 로그인을 수행하기 위한 과정 시작
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                // 사용자가 입력하는 로그인 정보가 들어가는 EditText 값을 받아서 String 형식으로 저장함
                EditText idText = (EditText)findViewById(R.id.idText);
                EditText passwordText = (EditText)findViewById(R.id.passwordText);
                final String idText_String = idText.getText().toString();
                final String passwordText_String = passwordText.getText().toString();

                //kw알기위한 접속 ??
                String kwurl = "http://210.115.182.155:3000/balanceOf/Cost";
                StringRequest kw_stringRequest = new StringRequest(Request.Method.GET, kwurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String kw_response) {
                        String response_dummy = kw_response.replaceAll("\\\\", "");
                        kw_response = response_dummy.substring(1, response_dummy.length()-1);
                        kw_server = kw_response;


                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        kwInFo kw_data = new kwInFo(kw_server);

                        mainIntent.putExtra("kw_data", kw_data);
                        LoginActivity.this.startActivityForResult(mainIntent, 101);


                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                //여기까지


                // * [회원정보 문서의 key : id+pw] URL에 진입해 id+pw를 key로 하여 value(원하는 회원정보 문서) 검색
                // api(?)에서 get으로 된 로그인 전용 함수 없이 json 파일을 받아오는 프로세스를 도저히 모르겠어서 couchDB와 연결해 데이터를 받아오기로 함
                // couchDB의 json 파일을 호출하기 위해서는 ip 주소와 couchDB의 port number(5984), Database 이름(mychannel_%24energy_network), 문서의 key 값이 필요함
                // 회원정보가 들어가는 문서의 경우 key는 id+pw로 하기로 정했으므로 사용자가 로그인 화면의 EditText에 입력한 id와 pw를 주소에 붙여줌
                String url = "http://210.115.182.155:3000/balanceOf/"+idText_String+"@@"+passwordText_String;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            String response_dummy = response.replaceAll("\\\\","");
                            response = response_dummy.substring(1, response_dummy.length()-1);
                            JsonParser jsonParser = new JsonParser();
                            JsonElement jsonElement = jsonParser.parse(response);
                            // # 05.02 start
                            id_server = jsonElement.getAsJsonObject().get("id").getAsString();
                            pw_server = jsonElement.getAsJsonObject().get("pw").getAsString();
                            name_server = jsonElement.getAsJsonObject().get("name").getAsString();
                            ph_server = jsonElement.getAsJsonObject().get("ph").getAsString();
                            email_server = jsonElement.getAsJsonObject().get("email").getAsString();
                            residentnum_server = jsonElement.getAsJsonObject().get("residentnum").getAsString();
                            bank_server = jsonElement.getAsJsonObject().get("bank").getAsString();
                            banknum_server = jsonElement.getAsJsonObject().get("banknum").getAsString();
                            carnum_server = jsonElement.getAsJsonObject().get("carnum").getAsString();


                            // # 05.02 until here

                            try {
                                if (id_server.equals(idText_String) && pw_server.equals(passwordText_String)){

                                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                                    mInFo data = new mInFo(id_server, pw_server, name_server, ph_server, email_server, residentnum_server, bank_server, banknum_server, carnum_server);
                                    mainIntent.putExtra("data", data);
                                    LoginActivity.this.startActivityForResult(mainIntent, 101);

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("아이디와 비밀번호를 다시 확인해주세요.").setNegativeButton("확인", null).create();
                                    dialog.show();
                                }

                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            dialog = builder.setMessage("아이디와 비밀번호를 다시 확인해주세요.").setNegativeButton("확인", null).create();
                            dialog.show();
                        }

                    }
                    // couchDB로부터 어떠한 문서도 전달받지 못한 경우 : couchDB에 사용자가 입력한 id+pw가 key인 문서가 없는 경우
                    // 알림창에 로그인 실패를 알리고 아무것도 하지 않음
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        dialog = builder.setMessage("아이디와 비밀번호를 다시 확인해주세요.").setNegativeButton("확인", null).create();
                        dialog.show();
                    }
                });

                if(AppHelper.RequestQueue == null){
                    AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
                }


                stringRequest.setShouldCache(false);
                AppHelper.RequestQueue.add(stringRequest);

                kw_stringRequest.setShouldCache(false);
                AppHelper.RequestQueue.add(kw_stringRequest);




            }
        });

    }


    /*public void InitializeMemberInfo(){     // # 05.02
        data.setId(id_server);
        data.setPw(pw_server);
        data.setName(name_server);
        data.setPh(ph_server);
        data.setEmail(email_server);
        data.setResidentnum(residentnum_server);
        data.setBank(bank_server);
        data.setBanknum(banknum_server);
    }*/

    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
    // until here (05/01 03:17)
}