package org.techtown.energychain;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditMemberInfoActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member_info);

        final Button energymainButton = (Button) findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button) findViewById(R.id.purchaseButton);
        final Button saleButton = (Button) findViewById(R.id.saleButton);
        final Button mydataButton = (Button) findViewById(R.id.mydataButton);

        // yeojin 05.03 01시
        final Intent passedIntent = getIntent();
        mInFo data = (mInFo) passedIntent.getParcelableExtra("data");

        TextView eif_id_TextView = (TextView) findViewById(R.id.eif_id_TextView);
        eif_id_TextView.setText(data.id_loggedIn);

        TextView eif_name_TextView = (TextView) findViewById(R.id.eif_name_TextView);
        eif_name_TextView.setText(data.name_loggedIn);

        EditText eif_EmailText = (EditText) findViewById(R.id.eif_EmailText);
        eif_EmailText.setText(data.email_loggedIn);

        EditText eif_phonenumber_editText = (EditText) findViewById(R.id.eif_phonenumber_editText);
        eif_phonenumber_editText.setText(data.ph_loggedIn);

        EditText eif_car_editText = (EditText) findViewById(R.id.eif_car_editText);
        eif_car_editText.setText(data.carnum_loggedIn);

        EditText eif_account_editText = (EditText) findViewById(R.id.eif_account_editText);
        eif_account_editText.setText(data.banknum_loggedIn);

        //eif_bank_spinner.setAdapter(data.bank_loggedIn);


        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mInFo data = (mInFo) passedIntent.getParcelableExtra("data");
                mainIntent.putExtra("data", data);
                startActivityForResult(mainIntent, 101);
            }
        });



        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), Purchase.class);
                mInFo data = (mInFo) passedIntent.getParcelableExtra("data");
                mainIntent.putExtra("data", data);
                startActivityForResult(mainIntent, 101);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(getApplicationContext(), Sale.class);
                mInFo data = (mInFo) passedIntent.getParcelableExtra("data");
                mainIntent.putExtra("data", data);
                startActivityForResult(mainIntent, 101);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MyPage.class);
                mInFo data = (mInFo) passedIntent.getParcelableExtra("data");
                mainIntent.putExtra("data", data);
                startActivityForResult(mainIntent, 101);
            }
        });

        spinner = (Spinner) findViewById(R.id.eif_bank_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.bank, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //eunhwa 0506 edit
        final Button editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest_update();
            }
        });
        //output

        if (AppHelper.RequestQueue == null) {
            AppHelper.RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }


    }

    public void sendRequest_update() {

        final Intent passedIntent = getIntent();
        mInFo data = (mInFo) passedIntent.getParcelableExtra("data");
        final String update_hash = data.id_loggedIn + "@@" + data.pw_loggedIn;

        final String url = "http://210.115.182.155:3000/Update";


        EditText eif_pw_EditText = (EditText) findViewById(R.id.eif_pw_EditText);
        EditText eif_pwcheck_editText = (EditText) findViewById(R.id.eif_pwcheck_editText);
        EditText eif_EmailText = (EditText) findViewById(R.id.eif_EmailText);
        EditText eif_phonenumber_editText = (EditText) findViewById(R.id.eif_phonenumber_editText);
        EditText eif_car_editText = (EditText) findViewById(R.id.eif_car_editText);
        Spinner eif_bank_spinner = (Spinner) findViewById(R.id.eif_bank_spinner);
        EditText eif_account_editText = (EditText) findViewById(R.id.eif_account_editText);

        final String eif_pw_EditText_String = eif_pw_EditText.getText().toString();
        final String eif_pwcheck_editText_String = eif_pwcheck_editText.getText().toString();
        final String eif_EmailText_String = eif_EmailText.getText().toString();
        final String eif_phonenumber_editText_String = eif_phonenumber_editText.getText().toString();
        final String eif_car_editText_String = eif_car_editText.getText().toString();
        final String eif_bank_spinner_String = eif_bank_spinner.getSelectedItem().toString();
        final String eif_account_editText_String = eif_account_editText.getText().toString();

        if (eif_pw_EditText_String.equals("")||eif_pwcheck_editText_String.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditMemberInfoActivity.this);
            dialog = builder.setMessage("변경할 비밀번호를 입력해주세요").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }


        if (!(eif_pw_EditText_String.equals(eif_pwcheck_editText_String))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditMemberInfoActivity.this);
            dialog = builder.setMessage("비밀번호를 확인해주세요.").setNegativeButton("확인", null).create();
            dialog.show();
            return;
        }



        StringRequest request = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EditMemberInfoActivity.this);
                        dialog = builder.setMessage("정보 수정에 성공했습니다.").setPositiveButton("확인", null).create();
                        Toast.makeText(getApplicationContext(), "정보 수정에 성공했습니다.", Toast.LENGTH_SHORT).show();

                        final Intent passedIntent = getIntent();
                        mInFo data = (mInFo) passedIntent.getParcelableExtra("data");

                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);

                        mInFo Edit_data = new mInFo(data.id_loggedIn, eif_pw_EditText_String, data.name_loggedIn,eif_phonenumber_editText_String, eif_EmailText_String, data.residentnum_loggedIn, eif_bank_spinner_String, eif_account_editText_String, eif_car_editText_String);
                        mainIntent.putExtra("data", Edit_data);

                        Toast.makeText(getApplicationContext(), eif_phonenumber_editText_String, Toast.LENGTH_LONG).show();
                        EditMemberInfoActivity.this.startActivityForResult(mainIntent, 101);

                        //dialog.show();
                    }

                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditMemberInfoActivity.this);
                dialog = builder.setMessage("회원 등록에 실패했습니다.").setNegativeButton("확인", null).create();
                Toast.makeText(getApplicationContext(), "회원 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();


                dialog.show();


            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Newhash", update_hash);
                params.put("Newpw", eif_pw_EditText_String);
                params.put("Newph", eif_phonenumber_editText_String);
                params.put("Newemail", eif_EmailText_String);
                params.put("Newbank", eif_bank_spinner_String);
                params.put("Newbanknum", eif_account_editText_String);
                params.put("Newcarnum", eif_car_editText_String);
                return params;
            }
        };
        request.setShouldCache(false);
        AppHelper.RequestQueue.add(request);
    }

}


    /* logic of edit_button
    public void onEditButtonClicked(View v) {
        EditText eif_pw_EditText = (EditText)findViewById(R.id.eif_pw_EditText);
        EditText eif_pwcheck_editText = (EditText)findViewById(R.id.eif_pwcheck_editText);
        EditText eif_EmailText = (EditText) findViewById(R.id.emailText);

        String eif_pw_String, eif_pwcheck_String, eif_Email_String;

        eif_pw_String = eif_pw_EditText.getText().toString();
        eif_pwcheck_String = eif_pwcheck_editText.getText().toString();
        eif_Email_String = eif_EmailText.getText().toString();

        if (eif_pw_String.equals(eif_pwcheck_String)) {
            Toast.makeText(getApplicationContext(), "개인정보가 변경되었습니다.", Toast.LENGTH_SHORT).show();
            if (eif_Email_String.matches("")){
            } else {
                // code for changing email on the server

            }
        } else {
            Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }*/

