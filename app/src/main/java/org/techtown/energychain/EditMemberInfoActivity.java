package org.techtown.energychain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditMemberInfoActivity extends AppCompatActivity {

    //private ArrayAdapter adapter;
    //private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member_info);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);

        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(EditMemberInfoActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(EditMemberInfoActivity.this, Purchase.class);
                startActivity(mainIntent);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(EditMemberInfoActivity.this, Sale.class);
                startActivity(mainIntent);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(EditMemberInfoActivity.this, MyPage.class);
                startActivity(mainIntent);
            }
        });

    }

    // logic of edit_button
    public void onEditButtonClicked(View v) {
        EditText eif_pw_EditText = (EditText)findViewById(R.id.eif_pw_EditText);
        EditText eif_pwcheck_editText = (EditText)findViewById(R.id.eif_pwcheck_editText);
        EditText eif_EmailText = (EditText) findViewById(R.id.eif_EmailText);

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
    }
}
