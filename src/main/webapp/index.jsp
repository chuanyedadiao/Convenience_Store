<%@ page import="com.chuanye.Dao.AssistantDao" %>
<%@ page import="com.chuanye.Dao.ShopOwnerDao" %>
<%@ page import="com.chuanye.VAL.Assistant" %>
<%@ page import="com.chuanye.VAL.ShopOwner" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    ShopOwnerDao shopOwnerDao = new ShopOwnerDao();
    AssistantDao assistantDao = new AssistantDao();
    ShopOwner shopOwner = null;
    Assistant assistant = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            String cookieName = c.getName();
            if ("name".equals(cookieName)) {
                String user = c.getValue();
                try {
                    shopOwner = shopOwnerDao.findWithId(user);
                    assistant = assistantDao.findWithId(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (shopOwner != null) {
                    session.setAttribute("info", shopOwner);
                    response.sendRedirect("ShopOwner/main.jsp");
                    return;
                }
                else if(assistant != null){
                    session.setAttribute("info", assistant);
                    response.sendRedirect("Assistant/main.jsp");
                    return;
                }
            }
        }
    }
    response.sendRedirect("login.jsp");
%>
</body>
</html>
