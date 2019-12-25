package com.chuanye.VIP;

import com.chuanye.Dao.VIPDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteVIPServlet")
public class DeleteVIPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        VIPDao vipDao = new VIPDao();

        String id = request.getParameter("id");
        try {
            vipDao.deleteAssistant(id);
            response.sendRedirect("vip_display");
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
