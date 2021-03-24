package com.example.a15831851229.temperature1;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.a15831851229.temperature1.Man;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class OutputActivity extends AppCompatActivity {
    private DBOpenHelper mDBOpenHelper;

    List<String> Time;
    List<String> Tem;
    List<String> Area;
    List<String> Spe;
    List<Man> orders = new ArrayList<Man>();
    private Button back;
    private Button Outexcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        ListView listView=(ListView)this.findViewById(R.id.listview) ;

        Time=new ArrayList<>();
        Tem=new ArrayList<>();
        Area=new ArrayList<>();
        Spe=new ArrayList<>();

        mDBOpenHelper = new DBOpenHelper(this);


        ArrayList<Man> dataone = mDBOpenHelper.getAllDataone();
        TextView Bao = (TextView) findViewById(R.id.zhengchang);
        Bao.setText("正常上报:"+dataone.size()+"人");
        TextView Wei = (TextView) findViewById(R.id.meiyou);
        int person= Integer.parseInt(String.valueOf(dataone.size()));
        Wei.setText(44-person+"人未填报");
        int Informal=0;
        for (int i = 0; i < dataone.size(); i++){
            Man man= dataone.get(i);
             if(Float.parseFloat(String.valueOf(man.getTem()))>38.0) {  Informal++;}
            Man order = new Man( man.getName() ,man.getTem(),  man.getDate(), man.getArea());
            orders.add(order);
            Time.add(man.getDate());
            Tem.add(man.getTem());
            Area.add(man.getArea());
            Spe.add(man.getSpecial());


        }
        TextView YiChang = (TextView) findViewById(R.id.yichang);
        YiChang.setText(Informal+"人体温异常");

        List<HashMap<String,Object>> data=new ArrayList<HashMap<String,Object>>();

        for (int i = 0; i < Time.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("TIME", Time.get(i));
            item.put("TEM", Tem.get(i));
            item.put("AREA", Area.get(i));
            item.put("SPE", Spe.get(i));
            data.add(item);
        }

        SimpleAdapter adapter=new SimpleAdapter(OutputActivity.this,data,R.layout.item,new String[]{"TIME","TEM","AREA","SPE"},new  int[]{R.id.TIME,R.id.TEM,R.id.AREA,R.id.SPE});
        listView.setAdapter(adapter);




        back=(Button)findViewById(R.id.backInput);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent=new Intent(OutputActivity.this,InputActivity.class);
                startActivity(intent);}

        });
        Outexcel=(Button)findViewById(R.id.excel);
        Outexcel.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {

                    ExcelUtil.writeExcel(OutputActivity.this,
                            orders, "excel_"+new Date().toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }
}
