package com.chuanye.VAL;

public class Assistant{
    private String Workid;
    private String name;
    private String gender;
    private String Password;
    private int age;
    private String phone;
    private String Wechat;

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    private String address;
    private float salary;
    private String worktime;

    public String getPassword(){ return Password;}

    public void setPassword(String password){this.Password = password;}

    @Override
    public String toString() {
        return "AssistantDao{" +
                "Workid='" + Workid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", Wechat='" + Wechat + '\'' +
                ", address='" + address + '\'' +
                ", salary=" +salary+ '\'' +
                '}';
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getWorkid() {
        return Workid;
    }

    public void setWorkid(String workid) {
        Workid = workid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return Wechat;
    }

    public void setWechat(String wechat) {
        Wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
