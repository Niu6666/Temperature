package com.example.a15831851229.temperature1;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15831851229 on 2021/3/22.
 */

//快捷键Alt+Insert
public class Man implements Serializable {
    public String name;
    public String tem;
    public String date;
    public String area;
    public String jw;
    public String special;



    @Override
    public String toString() {
        return "Man{" +
                "area='" + area + '\'' +
                ", name='" + name + '\'' +
                ", tem='" + tem + '\'' +
                ", date='" + date + '\'' +
                ", jw='" + jw + '\'' +
                ", special='" + special + '\'' +
                '}';
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSpecial() {

        return special;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJw() {

        return jw;
    }

    public void setJw(String jw) {
        this.jw = jw;
    }

    public void setArea(String area) {

        this.area = area;
    }

    public String getArea() {

        return area;
    }

    public Man(String name, String tem, String date, String area, String jw,String special) {
        this.name=name;
        this.tem=tem;
        this.date=date;
        this.area=area;
        this.jw=jw;
        this.special=special;

    }
    public Man(String name, String tem,String date, String area) {
        this.name=name;
        this.tem=tem;
        this.date=date;
        this.area=area;


    }
    public Man(JSONObject obj) {
        this.name = obj.optString("name");
        this.tem = obj.optString("tem");
        this.date = obj.optString("date");
        this.area = obj.optString("area");
        this.jw = obj.optString("jw");
        this.special = obj.optString("special");
    }
}
