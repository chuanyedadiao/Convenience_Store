<%@ page import="com.chuanye.VAL.ShopOwner" %><%--
  Created by IntelliJ IDEA.
  User: 徐帅比
  Date: 2019/12/11
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置信息</title>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link href="../resources/css/update.css" type="text/css" rel="stylesheet" />
</head>
<body>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">川页</h1>
<%
    ShopOwner shopOwner = (ShopOwner) session.getAttribute("info");
%>
<script>
    function changeValue(obj){
        $(obj).attr("value",$(obj).val());
    }
</script>
<div class="main">
    <form  role="form" action="/Convenience_Store/shopowner_update" method="post">
        <div class="form-group" align="center">
            ID<input class="form-control" type="text" readonly="readonly" name="id" placeholder=<%=shopOwner.getId()%> value=<%=shopOwner.getId()%>><br>
            Password<input class="form-control" type="text" readonly="readonly" name="password" placeholder=<%=shopOwner.getPassword()%> value=<%=shopOwner.getPassword()%>><br>
            Name<input class="form-control" type="text" name="name" placeholder=<%=shopOwner.getName()%> value=<%=shopOwner.getName()%> onchange="changeValue(this)"><br>
            Gender<input class="form-control" type="text" name="gender" placeholder=<%=shopOwner.getGender()%> value=<%=shopOwner.getGender()%> onchange="changeValue(this)"><br>
            Phone<input class="form-control" type="text" name="phone" placeholder=<%=shopOwner.getPhone()%> value=<%=shopOwner.getPhone()%> onchange="changeValue(this)"><br>
            Wechat<input class="form-control" type="text" name="wechat" placeholder=<%=shopOwner.getWechat()%> value=<%=shopOwner.getWechat()%> onchange="changeValue(this)"><br>
            <input type="submit" class="btn btn-success" value="提交">
            <input type="button" class="btn btn-info" value="取消" style="margin-left: 20px" onclick="window.location.href='main.jsp'">
        </div>
    </form>
</div>
<script src="../resources/js/jquery-3.4.1.min.js"></script>
<script src="../resources/js/popper.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
