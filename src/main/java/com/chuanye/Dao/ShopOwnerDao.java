package com.chuanye.Dao;

import com.chuanye.VAL.ShopOwner;

import java.sql.*;

public class ShopOwnerDao {
    private Connection conn = null;

    public ShopOwner checkAccount(String id, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from shopowner where id = '" + id + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        ShopOwner shopOwner = getShopOwner(rs);
        closeConnection();
        return shopOwner;
    }

    public ShopOwner findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from shopowner where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        ShopOwner shopOwner = getShopOwner(rs);
        closeConnection();
        return shopOwner;
    }

    public ShopOwner updateShopOwner(String id, String name, String gender, String wechat, String password) throws Exception{

        initConnection();
        String sql = "update ShopOwner set name=?, gender=?, wechat=?, password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, gender);
        ps.setString(3, wechat);
        ps.setString(4, password);
        ps.setString(5, id);
        ps.executeUpdate();
        ShopOwner ShopOwner = findWithId(id);
        closeConnection();
        return ShopOwner;
    }

    public void updateShopOwnerPassword(String id, String password) throws Exception{

        initConnection();
        String sql = "update ShopOwner set password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
    }

    private ShopOwner getShopOwner(ResultSet rs) throws SQLException {
        ShopOwner tea = null;
        if (rs.next()) {
            tea = new ShopOwner();
            tea.setId(rs.getString("id"));
            tea.setPassword(rs.getString("password"));
            tea.setName(rs.getString("name"));
            tea.setWechat(rs.getString("wechat"));
            tea.setGender(rs.getString("gender"));
            tea.setPhone(rs.getString("phone"));
        }
        return tea;
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/convenience_store?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "shuaibi990719");
    }

    private void closeConnection() throws Exception {
        conn.close();
    }
}
