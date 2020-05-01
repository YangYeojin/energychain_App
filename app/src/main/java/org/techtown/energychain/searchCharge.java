package org.techtown.energychain;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class searchCharge extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_PERMISSONS =1000;
    private GoogleMap mMap;
    private FusedLocationProviderClient mfusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_charge);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng CBNU = new LatLng(36.628041, 127.456695); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(CBNU).title("빅데이터연구실"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CBNU));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0432613636"));
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }

            }
        });

        //charge1
        LatLng Charge1 = new LatLng(36.629683, 127.464410 ); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge1).title("창신초등학교 충전소").snippet("충청북도 청주시 서원구 사창동 351"));

        //charge2
        LatLng Charge2 = new LatLng(36.620032, 127.448791); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge2).title("개신초등학교 충전소").snippet("충청북도 청주시 서원구 개신동 637"));

        //charge3
        LatLng Charge3 = new LatLng(36.614286, 127.465566); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge3).title("청주지방검찰청 충전소").snippet("충청북도 청주시 서원구 산남동 506"));

        //charge4
        LatLng Charge4 = new LatLng(36.629903, 127.469591); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge4).title("청주 푸르지오 5단지 충전소").snippet("충청북도 청주시 서원구 사직동 934"));

        //charge5
        LatLng Charge5 = new LatLng(36.639045, 127.448934); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge5).title("한국폴리텍대학 청주캠퍼스 충전소").snippet("충청북도 청주시 흥덕구 송정동 47"));

        //charge6
        LatLng charge6 = new LatLng(36.635087, 127.481456); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(charge6).title("청원교육지원청 충전소").snippet("충청북도 청주시 서원구 사직동 5-13"));

        //charge7
        LatLng Charge7 = new LatLng(36.637604, 127.470191); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge7).title("서원구청 충전소").snippet("충청북도 청주시 서원구 사직동 888"));

        //charge8
        LatLng Charge8 = new LatLng(36.619621, 127.441767); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge8).title("서원고등학교 충전소").snippet("충청북도 청주시 흥덕구 가경동 1580"));

        //charge9
        LatLng Charge9 = new LatLng(36.641468, 127.431478); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge9).title("흥덕구청 충전소").snippet("충청북도 청주시 흥덕구 복대동 3402"));

        //charge10
        LatLng Charge10 = new LatLng(36.637734, 127.464545); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge10).title("청주중앙여자고등학교 충전소").snippet("충청북도 청주시 서원구 사창동 227"));

        //charge11
        LatLng Charge11 = new LatLng(37.513756, 127.051472); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge11).title("삼성현대힐스테이트 2단지아파트 충전소").snippet("충청북도 청주시 서원구 산남동 506"));

        //charge12
        LatLng Charge12 = new LatLng(37.514624, 127.046665); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge12).title("언주중학교 충전소").snippet("서울특별시 강남구 삼성동 28"));

        //charge13
        LatLng Charge13 = new LatLng(37.517075, 127.056258); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge13).title("경기고등학교 충전소").snippet("서울특별시 강남구 삼성동 74-4"));

        //charge14
        LatLng Charge14 = new LatLng(37.505509, 127.061816); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge14).title("휘문중학교 충전소").snippet("서울특별시 강남구 대치동 952-1"));

        //charge15
        LatLng Charge15 = new LatLng(37.501793, 127.046937); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge15).title("진선여자고등학교 충전소").snippet("서울특별시 강남구 역삼동 713"));

        //charge16
        LatLng Charge16 = new LatLng(37.500794, 127.061142); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge16).title("대치현대아파트 충전소").snippet("서울특별시 강남구 대치동 974"));

        //charge17
        LatLng Charge17 = new LatLng(37.495622, 127.056471); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge17).title("단국대학교사범대학부속중학교 충전소").snippet("서울특별시 강남구 대치동 1013-3"));

        //charge18
        LatLng Charge18 = new LatLng(37.491962, 127.052249); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge18).title("중앙대학교사범대학부속고등학교 충전소").snippet("서울특별시 강남구 도곡동 산21"));

        //charge19
        LatLng Charge19 = new LatLng(37.507876, 127.057245); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge19).title("삼성동 홈플러스 충전소").snippet("서울특별시 강남구 삼성동 157-21"));

        //charge20
        LatLng Charge20 = new LatLng(37.511312, 127.066601); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge20).title("서울강남경찰서 충전소").snippet("서울특별시 강남구 삼성동 171-2"));

        //charge21
        LatLng Charge21 = new LatLng(37.517289, 127.047385); //위경도 표시
        mMap.addMarker(new MarkerOptions().position(Charge21).title("강남구청 충전소").snippet("서울특별시 강남구 삼성동 16-1"));


    }

    public void onLastLocationButtonClicked(View view) { //gps 공유 권한 설정
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSONS);
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSONS);
            return;
        }
        mfusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng myLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("현재위치"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation)); //내 위치로 보여줌
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_PERMISSONS:
                if(ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}
