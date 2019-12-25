<%@ page import="com.chuanye.VAL.ShopOwner" %><%--
  Created by IntelliJ IDEA.
  User: 徐帅比
  Date: 2019/11/25
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: 007
  Date: 2018/11/1
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>注册</title>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link href="../resources/css/register.css" type="text/css" rel="stylesheet" />
</head>
<body>
<script>
    function check(form){
        if (form.user.value === "") {
            alert("请输入Username！");
            return false;
        }
        if (form.name.value === "") {
            alert("请输入姓名！");
            return false;
        }
        if (form.gender.value === "") {
            alert("请输入性别！");
            return false;
        }
        if (form.phone.value === "") {
            alert("请输入联系电话！");
            return false;
        }
        if (form.age.value === "") {
            alert("请输入年龄！");
            return false;
        }
        return true;
    }
</script>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">欢迎来到川页之家啊哈哈哈哈</h1>
<div class="main">
    <h5 class="title">
        <a href="register.jsp" id="register">注册</a>
    </h5>
    <form action="/Convenience_Store/register" method="post" onsubmit="return check(this)">
        <div class="form-group">
            <input type="text" name="user" placeholder="Username" class="form-control user">
            <input type="text" name="name" placeholder="姓名" class="form-control name">
            <input type="text" name="gender" placeholder="性别" class="form-control gender">
            <input type="text" name="phone" class="form-control phone" placeholder="联系电话">
            <input type="text" name="age" class="form-control age" placeholder="年龄">
            <input type="text" name="worktime" class="form-control worktime" placeholder="工作时间">
            <input type="submit" value="注册" class="btn btn-primary btn-lg btn-block we">
        </div>
    </form>
</div>
<script src="../resources/js/jquery-3.2.1.min.js"></script>
<script src="../resources/js/popper.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>

</html>
