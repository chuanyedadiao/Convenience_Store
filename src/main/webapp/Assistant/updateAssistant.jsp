<%@ page import="com.chuanye.VAL.Assistant" %><%--
  Created by IntelliJ IDEA.
  User: 徐帅比
  Date: 2019/11/26
  Time: 9:42
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
    Assistant assistant = (Assistant) session.getAttribute("info");
%>
<script>
    function changeValue(obj){
        $(obj).attr("value",$(obj).val());
    }
</script>
<div class="main">
    <form  role="form" action="/Convenience_Store/assistant_update" method="post">
        <div class="form-group" align="center">
            WorkID<input class="form-control" type="text" readonly="readonly" name="id" placeholder=<%=assistant.getWorkid()%> value=<%=assistant.getWorkid()%>><br>
            Password<input class="form-control" type="text" readonly="readonly" name="password" placeholder=<%=assistant.getPassword()%> value=<%=assistant.getPassword()%>><br>
            Name<input class="form-control" type="text" name="name" placeholder=<%=assistant.getName()%> value=<%=assistant.getName()%> onchange="changeValue(this)"><br>
            Gender<input class="form-control" type="text" name="gender" placeholder=<%=assistant.getGender()%> value=<%=assistant.getGender()%> onchange="changeValue(this)"><br>
            Phone<input class="form-control" type="text" name="phone" placeholder=<%=assistant.getPhone()%> value=<%=assistant.getPhone()%> onchange="changeValue(this)"><br>
            Wechat<input class="form-control" type="text" name="wechat" placeholder=<%=assistant.getWechat()%> value=<%=assistant.getWechat()%> onchange="changeValue(this)"><br>
            Address<input class="form-control" type="text" name="address" placeholder=<%=assistant.getAddress()%> value=<%=assistant.getAddress()%> onchange="changeValue(this)"><br>
            Age<input class="form-control" type="text" name="age" placeholder=<%=assistant.getAge()%> value=<%=assistant.getAge()%> onchange="changeValue(this)"><br>
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