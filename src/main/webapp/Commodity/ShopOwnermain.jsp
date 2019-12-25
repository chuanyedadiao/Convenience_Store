<%@ page import="com.chuanye.VAL.Assistant" %>
<%@ page import="com.chuanye.VAL.Commodity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.chuanye.VAL.ShopOwner" %>
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
    ArrayList<Commodity> commos = (ArrayList<Commodity>) session.getAttribute("OnePageCommodity");
    Integer sumIndex = (Integer) session.getAttribute("sumCommodityIndex");
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
                <li class="current_page_item"><a href="/Convenience_Store/commodity_display">查询商品信息</a></li>
                <li><a href="../ShopOwner/main.jsp">个人信息</a></li>
                <li><a href="../ShopOwner/updateShopOwner.jsp?info=<%=shopOwner%>">修改信息</a> </li>
                <li><a href="../ShopOwner/register.jsp?info=<%=shopOwner%>">增加店员</a> </li>
                <li><a href="/Convenience_Store/assistant_display">查看店员</a> </li>
                <li><a href="/Convenience_Store/vip_display">会员信息</a> </li>
                <li><a onclick="return confirm('确认退出?');" href="/Convenience_Store/exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>商品信息管理</h2>
            <hr/>
            <button class="btn-add">添加商品</button>
            <div class="find">
                <form action="/Convenience_Store/commodity_display" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入商品名或商品号搜索">
                    <input class="find-btn" type="submit" value="搜索" >
                    <input type="submit"  class="find-btn" value="返回">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">商品号</th>
                    <th>商品名</th>
                    <th>商品类型</th>
                    <th>商品总数</th>
                    <th>商品价格</th>
                    <th></th>
                    <th>添加到购物车数量</th>
                </tr>
                <%
                    for (Commodity comm : commos) {
                %>
                <tr>
                    <form method="post" action="/Convenience_Store/commodity_update">
                        <td height="35"><%=comm.getId()%></td>
                        <td><input value="<%=comm.getName()%>" name="commname" class="table-input"></td>
                        <td><input value="<%=comm.getType()%>" name="commtype" class="table-input"></td>
                        <td><input value="<%=comm.getCount()%>" name="commcount" class="table-input"></td>
                        <td><input value="<%=comm.getPrice()%>" name="commprice" class="table-input" style="width: 110px"></td>
                        <input value="<%=comm.getId()%>" name="commno" type="hidden">
                        <td><input type="submit" class="update-btn" value="修改" href="/Convenience_Store/commodity_update">&nbsp;<a class="btn-delete"
                                                                                                                                   onclick="return confirm('确定要删除吗?');"
                                                                                                                                   href=<%="'/Convenience_Store/commodity_delete?id=" + comm.getId() + "'"%>>删除</a>
                        </td>
                    </form>
                    <form method="post" action="/Convenience_Store/send_buy_comm_infor">
                        <input value="<%=comm.getId()%>" name="buyno" type="hidden">
                        <td><input value="1" name="buycount" class="table-input" onchange="changeValue(this)" style="width: 110px"></td>
                        <td><input type="submit" class="update-btn" value="添加到购物车" href="/Convenience_Store/send_buy_comm_infor?id="<%=comm.getId()%>>
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
            <a href="/Convenience_Store/commodity_display?index=1">首页</a>
            <%
                for (int i=1; i<=sumIndex; i++){
            %>
            <a href="/Convenience_Store/commodity_display?index=<%=i%>">第<%=i%>页</a>
            <%
                }
            %>
            <a href="/Convenience_Store/commodity_display?index=<%=sumIndex%>">尾页</a>

        </div>
        <%
            }
        %>
        <a href="/Convenience_Store/showBills"><img src="../resources/img/buy.jpg" class="img-buy"/></a><!--展示账单，以及当前购买的内容-->
    </div>
</div>

<div id="add-dialog" title="添加商品信息">
    <form id="add-form" method="post">
        ID:<input name="id" type="text"><br>
        商品名:<input name="name" type="text"><br>
        商品类型:<input name="type" type="text"><br>
        商品数量:<input name="count" type="text"><br>
        商品价格:<input name="price" type="text">
        <hr>
        <input style="float: right" type="submit" value="取消" onclick="function x() {
          $('#add-dialog').dialog('close');
        }">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='/Convenience_Store/commodity_add'">
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