package com.example.a15831851229.temperature1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResgisterActivity extends AppCompatActivity implements View.OnClickListener{
    private DBOpenHelper mDBOpenHelper;
    private Button Res;
    private EditText Name;
    private EditText Number;
    private EditText Iphone;
    private EditText Class;
    private EditText Pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);
        initView();
        mDBOpenHelper = new DBOpenHelper(this);
        String username=Name.getText().toString().trim();
        String password=Number.getText().toString().trim();



    }

    private void initView() {
        Pas=(EditText) findViewById(R.id.pas);
        Name=(EditText) findViewById(R.id.name);
        Number=(EditText)findViewById(R.id.number);
        Class=(EditText)findViewById(R.id.banji);
        Iphone=(EditText)findViewById(R.id.iphone);
        Res=(Button)findViewById(R.id.button3);

        Res.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button3: //注册按钮
                String username =Name.getText().toString().trim();
                String password=Pas.getText().toString().trim();
                String number=Number.getText().toString().trim();
                String clas=Class.getText().toString().trim();
                String phone=Iphone.getText().toString().trim();

                if(username!=""&&password!="")
                {
                    mDBOpenHelper.add(username, password,number,phone,clas);
//                    EditText Yonghu=(EditText) findViewById(R.id.yonghu);
//                    Yonghu.setText(username);
                    Intent intent2 = new Intent(this, MainActivity.class);
                    startActivity(intent2);
                    finish();
                    Toast.makeText(this,  "信息完善，注册成功", Toast.LENGTH_SHORT).show();



                }else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;

        }


    }
}
