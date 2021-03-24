package com.example.a15831851229.temperature1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a15831851229.temperature1.Man;

import java.util.ArrayList;

/**
 * Created by 15831851229 on 2021/3/7.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public DBOpenHelper(Context context) {
        super(context, "db_test", null, 1);
        db = getReadableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT,"+"number TEXT,"+"phone TEXT,"+"clas TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Man("+"name TEXT,"+
                "tem TEXT,"+"date TEXT,"+"area TEXT,"+"jw TEXT,"+"special TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS Man");
        onCreate(db);
    }
    public void add(String name,String password,String number,String phone,String clas){

        db.execSQL("INSERT INTO user (name,password,number,phone,clas) VALUES(?,?,?,?,?)",new Object[]{name,password,number,phone,clas});
    }
    public void add(String name,String tem,String date,String  area,String jw,String special){
        db.execSQL("INSERT INTO Man (name,tem,date,area,jw,special) VALUES(?,?,?,?,?,?)",new Object[]{name,tem,date,area,jw,special});
    }
    public void delete(String name,String password,String number,String phone,String clas){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password+number+phone+clas);
    }
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    /**
     * 前三个没啥说的，都是一套的看懂一个其他的都能懂了
     * 下面重点说一下查询表user全部内容的方法
     * 我们查询出来的内容，需要有个容器存放，以供使用，
     * 所以定义了一个ArrayList类的list
     * 有了容器，接下来就该从表中查询数据了，
     * 这里使用游标Cursor，这就是数据库的功底了，
     * 在Android中我就不细说了，因为我数据库功底也不是很厚，
     * 但我知道，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，
     *                                                     最后是查询出来内容的排序方式："name DESC"
     * 游标定义好了，接下来写一个while循环，让游标从表头游到表尾
     * 在游的过程中把游出来的数据存放到list容器中
     * @return
     */
    public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String clas = cursor.getString(cursor.getColumnIndex("clas"));
            list.add(new User(name,password,number,phone,clas));
        }
        return list;
    }
    public ArrayList<Man> getAllDataone(){

        ArrayList<Man> list = new ArrayList<Man>();
        Cursor cursor = db.query("Man",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String tem = cursor.getString(cursor.getColumnIndex("tem"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String area = cursor.getString(cursor.getColumnIndex("area"));
            String jw = cursor.getString(cursor.getColumnIndex("jw"));
            String special = cursor.getString(cursor.getColumnIndex("special"));
            list.add(new Man(name,tem,date,area,jw,special));
        }
        return list;
    }
}
