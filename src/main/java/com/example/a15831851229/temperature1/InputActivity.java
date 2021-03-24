package com.example.a15831851229.temperature1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InputActivity extends AppCompatActivity implements View.OnClickListener {
    private LocationManager locationManager;
    private TextView tvLocation;
    private TextView textView;
    private EditText name;
    private EditText tem;
    private DBOpenHelper mDBOpenHelper;
     private Button input;
    private    Button output;
    private CheckBox none;
    private CheckBox gather;
    private CheckBox change;
    private CheckBox go;
    private String date_;
    private String area_;
    private String jw_;
    private String spe_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initView();
        mDBOpenHelper = new DBOpenHelper(this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        TextView time1 = (TextView) findViewById(R.id.textdate);
        time1.setText("填报日期时间:"+simpleDateFormat.format(date));
        date_=simpleDateFormat.format(date);
        textView=(TextView) findViewById(R.id.tv_area);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startRequestLocation();

    }

    private void startRequestLocation() {
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gps) {
            Toast.makeText(this, "请先设置界面开启Gps定位服务", Toast.LENGTH_LONG).show();
            return;

        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            showLocation(location);

        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);


    }
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    private void showLocation(Location location) {
        if (location != null) {

            tvLocation.setText("经度：" + location.getLongitude() + "\n" + "纬度：" + location.getLatitude());
            jw_="经度：" + location.getLongitude() + "\n" + "纬度：" + location.getLatitude();
        }
        Geocoder geocoder= new Geocoder(this, Locale.CHINA);
        try{
            List<Address> addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            for (Address address :addressList){
                textView.setText(address.getCountryName()+""+address.getLocality()+address.getFeatureName());//+address.getFeatureName()
                area_=address.getCountryName()+""+address.getLocality()+address.getFeatureName();
                Log.i("ansen",address.toString());

            }



        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void initView() {
        input=(Button)findViewById(R.id.inputMan);
        output=(Button)findViewById(R.id.output);
        name=(EditText) findViewById(R.id.editname);
        tem=(EditText)findViewById(R.id.edittem);
        none=(CheckBox)findViewById(R.id.none);
        gather=(CheckBox)findViewById(R.id.gather);
        change=(CheckBox)findViewById(R.id.changehome);
        go=(CheckBox)findViewById(R.id.high);

        input.setOnClickListener(this);
        output.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.inputMan: // 提交按钮
                StringBuilder special= new StringBuilder();
                if(none.isChecked()){
                    special.append(none.getText()+"");

                }
                if(gather.isChecked()){
                    special.append(gather.getText()+"");

                }
                if(change.isChecked()){
                    special.append(change.getText()+"");

                }
                if(go.isChecked()){
                    special.append(go.getText()+"");

                }
                String Name=name.getText().toString().trim();
                String Tem=tem.getText().toString().trim();
                String Date=date_;
                String Area=area_;
                String Jw=jw_;
                String Special=special.toString();
                //将用户名和密码加入到数据库中


                    mDBOpenHelper.add(Name,Tem,Date,Area,Jw,Special);

                    Toast.makeText(this,  "提交成功", Toast.LENGTH_SHORT).show();



                break;

            case R.id.output:
                Intent intent = new Intent(InputActivity.this, OutputActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
