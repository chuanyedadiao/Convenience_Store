package com.chuanye.Dao;

import com.chuanye.VAL.Bill;

import java.sql.*;
import java.util.ArrayList;

public class BillDao {

    private Connection conn = null;

    public void insertBill(String id,String name,int count,float price) throws Exception{
        initConnection();
        BillDao billDao = new BillDao();
        Statement stat = conn.createStatement();
        Bill bill = billDao.findWithId(id);
        if(bill!=null){
            String sql = "select * from bill where id = '"+id+"'";
            ResultSet rs = stat.executeQuery(sql);
            float total_price = price;
            int total_count = count;
            while(rs.next()){
                total_price = Float.parseFloat(rs.getString("total_price"))+price;
                total_count = Integer.parseInt(rs.getString("count"))+count;
            }
            System.out.println(total_price+"   "+total_count);
            sql = "update bill set count=?, total_price=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,total_count);
            ps.setFloat(2,total_price);
            ps.setString(3,id);
            ps.executeUpdate();
            closeConnection();
        }
        else{
            String sql = "insert into bill(id,name,count,total_price) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, count);
            ps.setFloat(4, price);
            int i = ps.executeUpdate();
            closeConnection();
        }
    }

    public int getBillsCount() throws Exception{
        initConnection();
        String sql = "select count(*) from bill";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public Bill findWithName(String name) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from bill where name = '" + name + "'";
        ResultSet rs = stat.executeQuery(sql);
        Bill bill = getBill(rs);
        closeConnection();
        return bill;
    }

    public void getMoreBill(ArrayList<Bill> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            Bill bill = new Bill();
            bill.setId(rs.getString("id"));
            bill.setCommName(rs.getString("name"));
            bill.setCommCount(Integer.parseInt(rs.getString("count")));
            bill.setCommPrice(Float.parseFloat(rs.getString("total_price")));
            al.add(bill);
        }
    }

    public ArrayList<Bill> UpLoadBills() throws Exception{
        ArrayList<Bill> al = new ArrayList<Bill>();
        initConnection();
        String sql = "SELECT * FROM bill";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs =  ps.executeQuery();
        getMoreBill(al, rs);
        closeConnection();
        return al;
    }

    public ArrayList<Bill> getOnePage(int page, int size) throws Exception{
        ArrayList<Bill> al = new ArrayList<Bill>();
        initConnection();
        String sql = "SELECT * FROM bill limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoreBill(al, rs);
        closeConnection();
        return al;
    }

    public Bill findWithId(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from bill where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Bill bill = getBill(rs);
        closeConnection();
        return bill;
    }

    public void updateBillInfo(String id, String name, int count, float price) throws Exception{

        initConnection();
        String sql = "update bill set name=?, count=?, total_price=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, count);
        ps.setFloat(3, price);
        ps.setString(4, id);
        ps.executeUpdate();
        closeConnection();
    }

    public boolean deleteBill(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from bill where id='"+id+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }
    public void clearBills() throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql ="truncate table bill";
        stat.executeUpdate(sql);
        closeConnection();
    }

    private Bill getBill(ResultSet rs) throws SQLException {
        Bill bill = null;
        if (rs.next()){
            bill = new Bill();
            bill.setId(rs.getString("id"));
            bill.setCommName(rs.getString("name"));
            bill.setCommCount(Integer.parseInt(rs.getString("count")));
            bill.setCommPrice(Float.parseFloat(rs.getString("total_price")));
        }
        return bill;
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/convenience_store?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "shuaibi990719");
    }

    private void closeConnection() throws Exception {
        conn.close();
    }
}
