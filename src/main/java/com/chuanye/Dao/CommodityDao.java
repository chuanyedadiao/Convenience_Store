package com.chuanye.Dao;

import com.chuanye.VAL.Commodity;

import java.sql.*;
import java.util.ArrayList;

public class CommodityDao {
    private Connection conn = null;

    public boolean insertCommodity(String id,String name,String type,int count,float price) throws Exception{
        initConnection();
        CommodityDao commodityDao = new CommodityDao();
        Commodity commodity1 = commodityDao.findWithId(id);
        if(commodity1!=null){
            return false;
        }
        else{
                String sql = "insert into commodity(id,name,type,count,price) values(?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, type);
                ps.setInt(4, count);
                ps.setFloat(5, price);
                int i = ps.executeUpdate();
                closeConnection();
                return i == 1;
            }
    }

    public boolean deleteCommodity(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from commodity where id='"+id+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }

    public void updateCommodityInfo(String id, String name, String type, int count, float price) throws Exception{

        initConnection();
        String sql = "update commodity set name=?, type=?, count=?, price=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, type);
        ps.setInt(3, count);
        ps.setFloat(4, price);
        ps.setString(5, id);
        ps.executeUpdate();
        closeConnection();
    }

    public Commodity findWithId(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from commodity where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Commodity commodity = getCommodity(rs);
        closeConnection();
        return commodity;
    }

    public Commodity findWithName(String name) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from commodity where name = '" + name + "'";
        ResultSet rs = stat.executeQuery(sql);
        Commodity commodity = getCommodity(rs);
        closeConnection();
        return commodity;
    }

    public ArrayList<Commodity> getOnePage(int page, int size) throws Exception{
        ArrayList<Commodity> al = new ArrayList<Commodity>();
        initConnection();
        String sql = "SELECT * FROM commodity limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoreCommodity(al, rs);
        closeConnection();
        return al;
    }

    public int getCommodityCount() throws Exception{
        initConnection();
        String sql = "select count(*) from commodity";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    private Commodity getCommodity(ResultSet rs) throws SQLException {
        Commodity commodity = null;
        if (rs.next()){
            commodity = new Commodity();
            commodity.setId(rs.getString("id"));
            commodity.setName(rs.getString("name"));
            commodity.setType(rs.getString("type"));
            commodity.setCount(Integer.parseInt(rs.getString("count")));
            commodity.setPrice(Float.parseFloat(rs.getString("price")));
        }
        return commodity;
    }

    private void getMoreCommodity(ArrayList<Commodity> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            Commodity commodity = new Commodity();
            commodity.setId(rs.getString("id"));
            commodity.setName(rs.getString("name"));
            commodity.setType(rs.getString("type"));
            commodity.setCount(Integer.parseInt(rs.getString("count")));
            commodity.setPrice(Float.parseFloat(rs.getString("price")));
            al.add(commodity);
        }
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/convenience_store?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "shuaibi990719");
    }

    private void closeConnection() throws Exception{
        conn.close();
    }
}
