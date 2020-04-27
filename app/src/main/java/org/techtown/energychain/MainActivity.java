package org.techtown.energychain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


        energymainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this, Purchase.class);
                startActivity(mainIntent);
            }
        });


        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MainActivity.this, Sale.class);
                startActivity(mainIntent);
            }
        });


        mydataButton.setOnClickListener(new View.OnClickListener() {
<<<<<<< HEAD
        @Override
        public void onClick(View v) {
            Intent mainIntent = new Intent(MainActivity.this, MyPage.class);
            startActivity(mainIntent);
            }
        });

//                energymain.setVisibility(View.GONE);
//                purchaseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                saleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                mydataButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment, new Mydata_Fragment());
//                fragmentTransaction.commit();
//            }
//        });
=======
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this, MyPage.class);
                startActivity(mainIntent);
            }
        });

        developerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this, developer.class);
                startActivity(mainIntent);
            }
        });

>>>>>>> master

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
