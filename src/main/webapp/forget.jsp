<%--
  Created by IntelliJ IDEA.
  User: 徐帅比
  Date: 2019/11/28
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link href="resources/css/forget.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<script>
    function check(form) {
        if (form.user.value === "") {
            alert("请输入账号！");
            return false;
        }
    }
</script>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">川页</h1>
<div class="main">
    <form role="form" action="forget" method="post">
        <div class="form-group" align="center">
            <input class="form-control" type="text" name="user" placeholder="输入用户名"><br>
            <input type="submit" class="btn btn-success" value="下一步">
            <input type="button"  class="btn btn-info" value="取消" style="margin-left: 20px" onclick="window.location.href='login.jsp'">
        </div>
    </form>
</div>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
