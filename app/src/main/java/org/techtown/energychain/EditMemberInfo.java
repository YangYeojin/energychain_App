package org.techtown.energychain;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;



import androidx.appcompat.app.AppCompatActivity;

public class EditMemberInfo extends AppCompatActivity {

    EditText eif_id_editText, eif_name_editText, eif_pw_EditText, eif_pwcheck_editText, eif_account_editText;
    ImageView eif_pw_false_img;

    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member_info);

        // change and check password
        eif_pw_EditText = (EditText)findViewById(R.id.eif_pw_EditText);
        eif_pwcheck_editText = (EditText)findViewById(R.id.eif_pwcheck_editText);
/*
        eif_pwcheck_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            /* 2020.04.23.01:04 can not password check T_T image file not inserted!! if else R.drawable check */
            /*@Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (eif_pw_EditText.getText().toString().equals(eif_pwcheck_editText.getText().toString())) {
                    eif_pwcheck_imageView.setImageResource(R.drawable.common_google_signin_btn_icon_light);
                } else {
                    eif_pwcheck_imageView.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        spinner = (Spinner)findViewById(R.id.OrgSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Org, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
