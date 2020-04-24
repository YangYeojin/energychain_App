package org.techtown.energychain;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EditMemberInfoActivity extends AppCompatActivity {

    EditText eif_id_TextView, eif_name_TextView, eif_pw_EditText, eif_pwcheck_editText, eif_account_editText;
    ImageView eif_pw_check_red, eif_pw_check_green;

    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member_info);

        // change and check password
        eif_pw_EditText = (EditText)findViewById(R.id.eif_pw_EditText);
        eif_pwcheck_editText = (EditText)findViewById(R.id.eif_pwcheck_editText);
        eif_pw_check_green = (ImageView)findViewById(R.id.eif_pw_check_green);
        eif_pw_check_red = (ImageView)findViewById(R.id.eif_pw_check_red);

        eif_pwcheck_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }
            // 2020.04.23.01:04 can not password check T_T image file not inserted!! if else R.drawable check
            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                String password = eif_pw_EditText.getText().toString();
                String confirm = eif_pwcheck_editText.getText().toString();

                if (password.equals(confirm)) {
                    eif_pw_check_green.setImageResource(R.drawable.ic_check_green_40dp);
                } else {
                    eif_pw_check_red.setImageResource(R.drawable.ic_cancel_red_40dp);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        spinner = (Spinner)findViewById(R.id.OrgSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Org, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
