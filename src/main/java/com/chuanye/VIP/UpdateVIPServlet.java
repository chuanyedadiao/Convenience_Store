package com.chuanye.VIP;

import com.chuanye.Dao.VIPDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateVIPServlet")
public class UpdateVIPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        VIPDao vipDao = new VIPDao();

        String id = request.getParameter("vipno");
        String name = request.getParameter("vipname");
        String phone = request.getParameter("vipphone");
        String level = request.getParameter("viplevel");
        try {
            vipDao.updateVIP(id,name,phone,level);
            response.sendRedirect("vip_display");
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
