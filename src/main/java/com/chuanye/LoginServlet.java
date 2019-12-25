package com.chuanye;

import com.chuanye.Dao.AssistantDao;
import com.chuanye.Dao.ShopOwnerDao;
import com.chuanye.VAL.Assistant;
import com.chuanye.VAL.ShopOwner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        AssistantDao assistantDao = new AssistantDao();
        Assistant assistant = null;
        ShopOwnerDao shopOwnerDao = new ShopOwnerDao();
        ShopOwner shopOwner = new ShopOwner();

        try {
            // 判断用户身份
            assistant = assistantDao.checkAccount(user, password);
            shopOwner = shopOwnerDao.checkAccount(user,password);
        }
        catch (Exception e) {
            out.print(e);
        }

        if (assistant != null) {
            //向session中添加用户信息
            session.setAttribute("info", assistant);

            //检查用户是否需要保持登录状态
            if (remember != null) {
                //发送cookie到客户端
                Cookie userCookie = new Cookie("id", user);
                userCookie.setMaxAge(10);
                response.addCookie(userCookie);
            }

            response.sendRedirect("Assistant/main.jsp");
        }
        else if (shopOwner != null) {
            //向session中添加用户信息
            session.setAttribute("info", shopOwner);

            //检查用户是否需要保持登录状态
            if (remember != null) {
                //发送cookie到客户端
                Cookie userCookie = new Cookie("name", user);
                userCookie.setMaxAge(10);
                response.addCookie(userCookie);
            }

            response.sendRedirect("ShopOwner/main.jsp");
        }
        else {
            out.print("<script>alert(\"用户名或密码错误！\");location.href = \"login.jsp\";</script>");
        }
    }

}
