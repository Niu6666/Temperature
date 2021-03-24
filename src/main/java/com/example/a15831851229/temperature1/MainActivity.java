package com.example.a15831851229.temperature1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private DBOpenHelper mDBOpenHelper;
     private Button log;
     private Button reg;
    private SQLiteDatabase db;   //用于创建数据库对象
    private static final String name = "JZ.db"; //数据库名称
    private static final int version = 1; //数据库版本

    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mDBOpenHelper = new DBOpenHelper(this);


    }

    private void initView() {
        log=(Button)findViewById(R.id.login);
        mEtLoginactivityUsername=(EditText)findViewById(R.id.yonghu) ;
        mEtLoginactivityPassword=(EditText)findViewById(R.id.mima) ;
        reg=(Button)findViewById(R.id.resgister);
        reg.setOnClickListener(this);
        log.setOnClickListener(this);

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.resgister:
                Intent intent=new Intent(MainActivity.this,ResgisterActivity.class);
                startActivity(intent);
                finish();
            case R.id.login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
             if(name!=""&&password!=""){
                 ArrayList<User> data = mDBOpenHelper.getAllData();
                 boolean match = false;
                 for (int i = 0; i < data.size(); i++) {
                     User user = data.get(i);
                     if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                         match = true;
                         break;
                     } else {
                         match = false;

                     }

                 }
                 if (match){
                     Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                     Intent intentone = new Intent(this, InputActivity.class);
                     startActivity(intentone);
                     finish();//销毁此Activity



                 }else{
                     Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();

                 }

             }else{
                 Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();

             }
                break;



        }

    }

}