package com.chuanye.Dao;

import com.chuanye.VAL.Assistant;
import java.sql.*;

import java.util.ArrayList;

public class AssistantDao {
    private Connection conn = null;

    public Assistant checkAccount(String id, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from assistant where id = '" + id + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Assistant assistant = getAssistant(rs);
        closeConnection();
        return assistant;
    }

    public Assistant findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from assistant where id = '" + id +"'";
        ResultSet rs = stat.executeQuery(sql);
        Assistant assistant = getAssistant(rs);
        closeConnection();
        return assistant;
    }

    public ArrayList<Assistant> findWithName(String name) throws Exception{
        ArrayList<Assistant> al = new ArrayList<>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from assistant where name = '" + name +"'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreAssistant(al, rs);
        closeConnection();
        return al;
    }

    public Assistant insertAssistant(String id, String name, String gender, String phone, String password, String address, String wechat, int age,float salary,String worktime) throws Exception {
        Assistant assistant = findWithId(id);
        if(assistant!=null){
            return assistant;
        }
        initConnection();
        String sql = "insert into assistant(id,name,gender,phone,password,address,wechat,age,salary,work_time) values(?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, gender);
        ps.setString(4, phone);
        ps.setString(5, password);
        ps.setString(6, address);
        ps.setString(7, wechat);
        ps.setInt(8,age);
        ps.setFloat(9,salary);
        ps.setString(10,worktime);
        ps.executeUpdate();
        closeConnection();
        return assistant;
    }

    public boolean deleteAssistant(String id) throws Exception{

        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from assistant where id='"+id+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }

    public int getAssistantCount() throws Exception{
        initConnection();
        String sql = "select count(*) from assistant";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public ArrayList<Assistant> getOnePage(int page, int size) throws Exception{
        ArrayList<Assistant> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM assistant limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoreAssistant(al, rs);
        closeConnection();
        return al;
    }


    public Assistant updateAssistant(String id, String name, String gender, String phone,String address,String wechat, int age) throws Exception{

        initConnection();
        String sql = "update assistant set name=?, gender=?, phone=?, address=?, wechat=?, age=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, gender);
        ps.setString(3, phone);
        ps.setString(4, address);
        ps.setString(5,wechat);
        ps.setInt(6,age);
        ps.setString(7,id);
        ps.executeUpdate();
        AssistantDao assistantDao = new AssistantDao();
        Assistant assistant = new Assistant();
        assistant = assistantDao.findWithId(id);
        closeConnection();
        return assistant;
    }

    public Assistant updateAssistantByOwner(String id, String name, String gender, String phone,String address,String wechat, int age,float salary,String worktime) throws Exception{

        initConnection();
        String sql = "update assistant set name=?, gender=?, phone=?, address=?, wechat=?, age=?,salary=?,work_time=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, gender);
        ps.setString(3, phone);
        ps.setString(4, address);
        ps.setString(5,wechat);
        ps.setInt(6,age);
        ps.setFloat(7,salary);
        ps.setString(8,worktime);
        ps.setString(9,id);
        ps.executeUpdate();
        AssistantDao assistantDao = new AssistantDao();
        Assistant assistant = new Assistant();
        assistant = assistantDao.findWithId(id);
        closeConnection();
        return assistant;
    }

    public Assistant updateAssistantPassword(String id, String password, String pre_password) throws Exception{

        Assistant assistant = checkAccount(id,pre_password);
        if(assistant == null){
            return assistant;
        }
        initConnection();
        String sql = "update assistant set password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
        return assistant;
    }

    public void resetAssistantPassword(String id, String password) throws Exception{
        initConnection();
        String sql = "update assistant set password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
    }

    private Assistant getAssistant(ResultSet rs) throws SQLException {
        Assistant assistant = null;
        if (rs.next()) {
            assistant = new Assistant();
            assistant.setWorkid(rs.getString("id"));
            assistant.setPassword(rs.getString("password"));
            assistant.setName(rs.getString("name"));
            assistant.setPhone(rs.getString("phone"));
            assistant.setGender(rs.getString("gender"));
            assistant.setAddress(rs.getString("address"));
            assistant.setWechat(rs.getString("wechat"));
            assistant.setAge(Integer.valueOf(rs.getString("age")));
            assistant.setSalary(Float.valueOf(rs.getString("salary")));
            assistant.setWorktime(rs.getString("work_time"));
        }
        return assistant;
    }

    private void getMoreAssistant(ArrayList<Assistant> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            Assistant assistant = new Assistant();
            assistant.setWorkid(rs.getString("id"));
            assistant.setPassword(rs.getString("password"));
            assistant.setName(rs.getString("name"));
            assistant.setPhone(rs.getString("phone"));
            assistant.setGender(rs.getString("gender"));
            assistant.setAddress(rs.getString("address"));
            assistant.setWechat(rs.getString("wechat"));
            assistant.setAge(Integer.valueOf(rs.getString("age")));
            assistant.setSalary(Float.valueOf(rs.getString("salary")));
            assistant.setWorktime(rs.getString("work_time"));
            al.add(assistant);
        }
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/convenience_store?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "shuaibi990719");
    }

    private void closeConnection() throws Exception {
        conn.close();
    }

}
