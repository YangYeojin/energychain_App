package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button energymainButton = (Button)findViewById(R.id.energymainButton);
        final Button purchaseButton = (Button)findViewById(R.id.purchaseButton);
        final Button saleButton = (Button)findViewById(R.id.saleButton);
        final Button mydataButton = (Button)findViewById(R.id.mydataButton);
        final Button developerButton =(Button)findViewById(R.id.developerButton);

        final Intent passedIntent = getIntent();

        final Intent kw_Intent = getIntent();
        kwInFo kw_data = (kwInFo)kw_Intent.getParcelableExtra("kw_data");
        TextView main_kw = (TextView) findViewById(R.id.main_kw);
        main_kw.setText(kw_data.kw_loggedIn);

        final Intent mytoken_Intent = getIntent();
        mytokenInFo mytoken_data = (mytokenInFo)mytoken_Intent.getParcelableExtra("mytoken_data");
        TextView main_mytoken =(TextView)findViewById(R.id.main_mytoken_data);
        main_mytoken.setText(mytoken_data.mytoken_loggedIn);


        final Intent mykw_Intent = getIntent();
        mykwInFo mykw_data = (mykwInFo)mykw_Intent.getParcelableExtra("mykw_data");
        TextView main_mykw = (TextView) findViewById(R.id.main_mykw);
        main_mykw.setText(mykw_data.mykw_loggedIn);



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

        developerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this, developer.class);
                startActivity(mainIntent);
            }
        });


        TextView searchChargeButton = (TextView)findViewById(R.id.searchChargeButton);
        searchChargeButton.setOnClickListener(new TextView.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent searchChargeIntent = new Intent(MainActivity.this, searchCharge.class);
                startActivity(searchChargeIntent);
            }
        });



    }




    private long lastTimeBackPressed; //뒤로 두번 클릭시 앱 종료
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-lastTimeBackPressed<1500){
            finish();
            return;
        }
        Toast.makeText(this, "한 번 더 누르면 로그인 페이지로 이동", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed=System.currentTimeMillis();
    }
}
