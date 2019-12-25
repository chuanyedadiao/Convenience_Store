<%@ page import="com.chuanye.VAL.Assistant" %>
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
    Assistant assistant = (Assistant) session.getAttribute("info");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=assistant.getWorkid()%>.jpg"/>
            <h1><%=assistant.getName()%></h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="/Convenience_Store/commodity_display">查询商品信息</a></li>
                <li class="current_page_item"><a href="main.jsp">个人信息</a></li>
                <li><a href="updateAssistant.jsp?info=<%=assistant%>">修改信息</a> </li>
                <li><a href="resetPassword.jsp?info=<%=assistant%>">更改密码</a> </li>
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
                    <th>年龄</th>
                    <th>住址</th>
                    <th>电话</th>
                    <th>微信</th>
                    <th>工作时间</th>
                    <th>薪水</th>
                </tr>
                <tr>
                    <td height="35"><%=assistant.getWorkid()%></td>
                    <td><%=assistant.getName()%></td>
                    <td><%=assistant.getGender()%></td>
                    <td><%=assistant.getAge()%></td>
                    <td><%=assistant.getAddress()%></td>
                    <td><%=assistant.getPhone()%></td>
                    <td><%=assistant.getWechat()%></td>
                    <td><%=assistant.getWorktime()%></td>
                    <td><%=assistant.getSalary()%></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
