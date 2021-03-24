package com.example.a15831851229.temperature1;

/**
 * Created by 15831851229 on 2021/3/7.
 */

public class User {
    private String name;            //用户名
    private String password;        //密码
    private String number;      //学号
    private String phone;      //手机号
    private String clas;      //班级

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", clas='" + clas + '\'' +
                '}';
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public void setNumber(String number) {

        this.number = number;
    }

    public String getPhone() {

        return phone;
    }

    public String getNumber() {

        return number;
    }

    public User(String name, String password, String number, String phone, String clas) {
        this.name = name;
        this.password = password;
        this.number = number;
        this.phone = phone;
        this.clas = clas;
    }




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

