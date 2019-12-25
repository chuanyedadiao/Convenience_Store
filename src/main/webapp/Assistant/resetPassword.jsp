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
    <title>重置密码</title>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link href="../resources/css/forget.css" type="text/css" rel="stylesheet" />
</head>
<body>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">川页</h1>
<%
    Assistant assistant = (Assistant) session.getAttribute("info");
%>
<script>
    function check(form){
        if (form.pre_password.value === "") {
            alert("请输入原密码！");
            return false;
        }
        if (form.password.value === "") {
            alert("请输入密码！");
            return false;
        }
        return true;
    }
</script>
<div class="main">
    <form  role="form" action="/Convenience_Store/assistant_update_password" method="post">
        <div class="form-group" align="center">
            <input type="hidden" name="id" value="<%=assistant.getWorkid()%>">
            <input class="form-control" type="password" name="pre_password" placeholder="原始密码"><br>
            <input class="form-control" type="password" name="password" placeholder="新密码"><br>
            <input type="submit" class="btn btn-success" value="提交">
            <input type="button" class="btn btn-info" value="取消" style="margin-left: 20px" onclick="window.location.href='main.jsp'">
        </div>
    </form>
</div>
<script src="../resources/js/jquery-3.2.1.min.js"></script>
<script src="../resources/js/popper.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>

