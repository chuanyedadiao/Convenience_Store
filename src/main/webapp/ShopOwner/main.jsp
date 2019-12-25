<%@ page import="com.chuanye.VAL.ShopOwner" %>
<%--
  Created by IntelliJ IDEA.
  User: 徐帅比
  Date: 2019/11/25
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    ShopOwner shopOwner = (ShopOwner) session.getAttribute("info");
    session.setAttribute("info",shopOwner);
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=shopOwner.getId()%>.jpg"/>
            <h1><%=shopOwner.getName()%></h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="/Convenience_Store/commodity_display">查询商品信息</a></li>
                <li class="current_page_item"><a href="main.jsp">个人信息</a></li>
                <li><a href="updateShopOwner.jsp?info=<%=shopOwner%>">修改信息</a> </li>
                <li><a href="register.jsp?info=<%=shopOwner%>">增加店员</a> </li>
                <li><a href="/Convenience_Store/assistant_display">查看店员</a> </li>
                <li><a href="/Convenience_Store/vip_display">会员信息</a> </li>
                <li><a onclick="return confirm('确认退出?');" href="/Convenience_Store/exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>个人信息</h2>
            <hr/>
        </div>
        <div class="table">
            <table width="800" frame="box" align="center">
                <tr>
                    <th height="35">工号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>电话</th>
                    <th>微信</th>
                </tr>
                <tr>
                    <td height="35"><%=shopOwner.getId()%></td>
                    <td><%=shopOwner.getName()%></td>
                    <td><%=shopOwner.getGender()%></td>
                    <td><%=shopOwner.getPhone()%></td>
                    <td><%=shopOwner.getWechat()%></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
