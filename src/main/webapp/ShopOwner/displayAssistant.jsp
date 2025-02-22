<%@ page import="com.chuanye.VAL.ShopOwner" %>
<%@ page import="com.chuanye.VAL.Assistant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>diaplay_assistant</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    ShopOwner shopOwner = (ShopOwner) session.getAttribute("info");
    ArrayList<Assistant> assistants = (ArrayList<Assistant>) session.getAttribute("OnePageAssistant");
    Integer sumIndex = (Integer) session.getAttribute("sumAssistantIndex");
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
                <li class="current_page_item"><a href="/Convenience_Store/assistant_display">查看店员</a> </li>
                <li><a href="/Convenience_Store/vip_display">会员信息</a> </li>
                <li><a onclick="return confirm('确认退出?');" href="/Convenience_Store/exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>店员信息管理</h2>
            <hr/>
            <div class="find">
                <form action="/Convenience_Store/assistant_display" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入员工工号搜索">
                    <input class="find-btn" type="submit" value="搜索" >
                    <input type="submit"  class="btn btn-info" value="返回">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">工号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>联系电话</th>
                    <th>微信</th>
                    <th>家庭住址</th>
                    <th>薪水</th>
                    <th>工作时间</th>
                </tr>
                <%
                    for (Assistant assistant : assistants) {
                        String wechat,address;
                        if(assistant.getWechat()==null){wechat = "无";}else{wechat = assistant.getWechat();}
                        if(assistant.getWechat()==null){address = "无";}else{address = assistant.getAddress();}
                %>
                <tr>
                    <form method="post" action="/Convenience_Store/assistant_update_by_owner">
                        <td height="35"><%=assistant.getWorkid()%></td>
                        <td><input value="<%=assistant.getName()%>" name="astname" class="table-input"></td>
                        <td><input value="<%=assistant.getGender()%>" name="astgender" class="table-input"></td>
                        <td><input value="<%=assistant.getAge()%>" name="astage" class="table-input"></td>
                        <td><input value="<%=assistant.getPhone()%>" name="astphone" class="table-input-TEL"></td>
                        <td><input value="<%=wechat%>" name="astwechat" class="table-input"></td>
                        <td><input value="<%=address%>" name="astaddress" class="table-input"></td>
                        <td><input value="<%=assistant.getSalary()%>" name="astsalary" class="table-input"></td>
                        <td><input value="<%=assistant.getWorktime()%>" name="astworktime" class="table-input" style="width: 110px"></td>
                        <input value="<%=assistant.getWorkid()%>" name="astno" type="hidden">
                        <td><input type="submit" class="update-btn" value="修改" href="/Convenience_Store/assistant_update_by_owner">&nbsp;<a class="btn-delete"
                                                                                                                                            onclick="return confirm('确定要删除吗?');"
                                                                                                                                            href=<%="'/Convenience_Store/assistant_delete?id=" + assistant.getWorkid() + "'"%>>删除</a>
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
            <a href="/Convenience_Store/assistant_display?index=1">首页</a>
            <%
                for (int i=1; i<=sumIndex; i++){
            %>
            <a href="/Convenience_Store/assistant_display?index=<%=i%>">第<%=i%>页</a>
            <%
                }
            %>
            <a href="/Convenience_Store/assistant_display?index=<%=sumIndex%>">尾页</a>
        </div>
        <%
            }
        %>
    </div>
</div>


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