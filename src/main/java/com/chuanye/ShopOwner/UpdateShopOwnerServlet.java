package com.chuanye.ShopOwner;

import com.chuanye.Dao.ShopOwnerDao;
import com.chuanye.VAL.ShopOwner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateShopOwnerServlet")
public class UpdateShopOwnerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        ShopOwnerDao shopOwnerDao = new ShopOwnerDao();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String wechat = request.getParameter("wechat");
        String password = request.getParameter("password");
        try {
            ShopOwner shopOwner = shopOwnerDao.updateShopOwner(id, name, gender, wechat, password);
            session.setAttribute("info", shopOwner);
            out.print("<script>alert(\"保存成功！\");location.href = \"ShopOwner/main.jsp\";</script>");
        }
        catch (Exception e){
            out.print(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
