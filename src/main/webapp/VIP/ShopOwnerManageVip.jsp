<%@ page import="com.chuanye.VAL.ShopOwner" %>
<%@ page import="com.chuanye.VAL.VIP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    ShopOwner shopOwner = (ShopOwner) session.getAttribute("info");
    ArrayList<VIP> vips = (ArrayList<VIP>) session.getAttribute("OnePageVIP");
    Integer sumIndex = (Integer) session.getAttribute("sumVIPIndex");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=shopOwner.getId()%>.jpg"/>
            <h1><%=shopOwner.getName()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="/Convenience_Store/commodity_display">查询商品信息</a></li>
                <li><a href="main.jsp">个人信息</a></li>
                <li><a href="updateShopOwner.jsp?info=<%=shopOwner%>">修改信息</a> </li>
                <li><a href="register.jsp?info=<%=shopOwner%>">增加店员</a> </li>
                <li><a href="/Convenience_Store/assistant_display">查看店员</a> </li>
                <li class="current_page_item"><a href="/Convenience_Store/vip_display">会员信息</a> </li>
                <li><a onclick="return confirm('确认退出?');" href="/Convenience_Store/exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>会员信息管理</h2>
            <hr/>
            <button class="btn-add">添加会员</button>
            <div class="find">
                <form action="/Convenience_Store/vip_display" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入会员电话搜索">
                    <input class="find-btn" type="submit" value="搜索" >
                    <input type="submit"  class="btn btn-info" value="返回">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">会员</th>
                    <th>会员名</th>
                    <th>会员电话</th>
                    <th>会员等级</th>
                </tr>
                <%
                    for (VIP vip : vips) {
                %>
                <tr>
                    <form method="post" action="/Convenience_Store/vip_update">
                        <td height="35"><%=vip.getId()%></td>
                        <td><input value="<%=vip.getName()%>" name="vipname" class="table-input"></td>
                        <td><input value="<%=vip.getPhone()%>" name="vipphone" class="table-input-TEL"></td>
                        <td><input value="<%=vip.getLevel()%>" name="viplevel" class="table-input" style="width: 110px"></td>
                        <input value="<%=vip.getId()%>" name="vipno" type="hidden">
                        <td><input type="submit" class="update-btn" value="修改" href="/Convenience_Store/vip_update">&nbsp;<a class="btn-delete"
                                                                                                                             onclick="return confirm('确定要删除吗?');"
                                                                                                                             href=<%="'/Convenience_Store/vip_delete?id=" + vip.getId() + "'"%>>删除</a>
                        </td>
                    </form>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <%
            if (sumIndex > 1){
        %>
        <div id="index">
            <a href="/Convenience_Store/vip_display?index=1">首页</a>
            <%
                for (int i=1; i<=sumIndex; i++){
            %>
            <a href="/Convenience_Store/vip_display?index=<%=i%>">第<%=i%>页</a>
            <%
                }
            %>
            <a href="/Convenience_Store/vip_display?index=<%=sumIndex%>">尾页</a>
        </div>
        <%
            }
        %>
    </div>
</div>

<div id="add-dialog" title="添加VIP信息">
    <form id="add-form" method="post">
        VIPid:<input name="id" type="text"><br>
        VIP名:<input name="name" type="text"><br>
        VIP电话:<input name="phone" type="text"><br>
        VIP等级:<input name="level" type="text"><br>
        <hr>
        <input style="float: right" type="submit" value="取消" onclick="function x() {
          $('#add-dialog').dialog('close');
        }">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='/Convenience_Store/vip_add'">
    </form>
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>

<script>
    $('#add-dialog').dialog({
        width: 310,
        autoOpen: false,
        draggable: false,
        modal: true,
        resizable: false
    });
    $('.btn-add').click(function () {
        $('#add-dialog').dialog('open');
    });
</script>
</body>
</html>