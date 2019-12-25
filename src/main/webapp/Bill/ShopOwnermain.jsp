<%@ page import="com.chuanye.VAL.Bill" %>
<%@ page import="com.chuanye.VAL.ShopOwner" %>
<%@ page import="java.math.BigDecimal" %>
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
    ArrayList<Bill> bills = (ArrayList<Bill>) session.getAttribute("OnePageBill");
    Integer sumIndex = (Integer) session.getAttribute("sumBillIndex");
    session.setAttribute("info",shopOwner);
%>
<script>
    function changeValue(obj){
        $(obj).attr("value",$(obj).val());
    }
</script>

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
            <h2>账单</h2>
            <hr/>
            <form action="/Convenience_Store/commodity_display" method="post">
                <input type="submit" class="back-btn" value="返回继续添加">
            </form>
            <div class="find">
                <form action="/Convenience_Store/showBills" method="post">
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
                    <th>商品总数</th>
                    <th>单价</th>
                </tr>
                <%
                    for (Bill bill : bills) {
                        Double num = (double)bill.getCommPrice()/bill.getCommCount();
                        BigDecimal bd = new BigDecimal(num);
                        num = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        float temp = num.floatValue();
                        Double num1 = (double)bill.getCommPrice();
                        BigDecimal bd1 = new BigDecimal(num1);
                        num1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        float temp1 = num1.floatValue();
                %>
                <tr>
                    <form method="post" action="/Convenience_Store/bill_update">
                        <td height="35"><%=bill.getId()%></td>
                        <td height="35"><%=bill.getCommName()%></td>
                        <td><input value="<%=bill.getCommCount()%>" name="finalCount" class="table-input"></td>
                        <td height="35"><%=temp%></td>
                        <input value="<%=bill.getCommName()%>" name="finalName" type="hidden">
                        <input value="<%=temp%>" name="finalPrice" type="hidden">
                        <input value="<%=temp1%>" name="finalTotalPrice" type="hidden">
                        <input value="<%=bill.getId()%>" name="finalID" type="hidden">
                        <td><input type="submit" class="update-btn" value="修改" href="/Convenience_Store/bill_update">&nbsp;<a
                                class="delete-btn" onclick="return confirm('确定要删除吗?');"
                                href=<%="'/Convenience_Store/bill_delete?id=" + bill.getId() + "'"%>>删除</a>
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
            <a href="/Convenience_Store/showBills?index=1">首页</a>
            <%
                for (int i=1; i<=sumIndex; i++){
            %>
            <a href="/Convenience_Store/showBills?index=<%=i%>">第<%=i%>页</a>
            <%
                }
            %>
            <a href="/Convenience_Store/showBills?index=<%=sumIndex%>">尾页</a>
        </div>
        <%
            }
        %>
        <a class="final_btn" href="/Convenience_Store/uploadBills" style="float:right"><img src="../resources/img/check.jpg" class="img-check"/></a>
    </div>
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>
</body>
</html>