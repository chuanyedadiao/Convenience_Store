package com.chuanye.Dao;


import com.chuanye.VAL.VIP;

import java.sql.*;
import java.util.ArrayList;

public class VIPDao {
    private Connection conn = null;

    public VIP findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from vip where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        VIP vip = getVIP(rs);
        closeConnection();
        return vip;
    }

    public VIP findWithPhone(String phone) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from vip where phone = '" + phone + "'";
        ResultSet rs = stat.executeQuery(sql);
        VIP vip = getVIP(rs);
        closeConnection();
        return vip;
    }

    public ArrayList<VIP> findWithName(String name) throws Exception{
        ArrayList<VIP> al = new ArrayList<>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from vip where name = '" + name + "'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreVIP(al, rs);
        closeConnection();
        return al;
    }

    public boolean insertAssistant(String id, String name, String phone, String level) throws Exception {
        VIP vip = findWithId(id);
        if(vip!=null) return false;
        initConnection();
        String sql = "insert into vip(id, name, phone, level) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, phone);
        ps.setString(4, level);
        ps.executeUpdate();
        closeConnection();
        return true;
    }

    public boolean deleteAssistant(String id) throws Exception{

        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from vip where id='"+id+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }

    public int getVIPCount() throws Exception{
        initConnection();
        String sql = "select count(*) from vip";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public ArrayList<VIP> getOnePage(int page, int size) throws Exception{
        ArrayList<VIP> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM vip limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoreVIP(al, rs);
        closeConnection();
        return al;
    }


    public VIP updateVIP(String id, String name,String phone,String level) throws Exception{

        initConnection();
        String sql = "update vip set name=?,phone=?,level=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, phone);
        ps.setString(3, level);
        ps.setString(4,id);
        ps.executeUpdate();
        VIP assistant = findWithId(id);
        closeConnection();
        return assistant;
    }

    private VIP getVIP(ResultSet rs) throws SQLException {
        VIP vip = null;
        if (rs.next()) {
            vip = new VIP();
            vip.setId(rs.getString("id"));;
            vip.setName(rs.getString("name"));
            vip.setPhone(rs.getString("phone"));
            vip.setLevel(rs.getString("level"));
        }
        return vip;
    }

    private void getMoreVIP(ArrayList<VIP> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            VIP vip = new VIP();
            vip.setId(rs.getString("id"));
            vip.setName(rs.getString("name"));
            vip.setPhone(rs.getString("phone"));
            vip.setLevel(rs.getString("level"));
            al.add(vip);
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
