package com.chuanye.VIP;

import com.chuanye.Dao.VIPDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddVIPServlet")
public class AddVIPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        VIPDao vipDao = new VIPDao();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String level = request.getParameter("level");
        System.out.println(id+"  "+name+"  "+phone+"   "+level);

        try {
            boolean flag = vipDao.insertAssistant(id, name, phone, level);
            if (flag){
                response.sendRedirect("vip_display");
            }
            else{
                out.print("<script>alert(\"该会员已存在\");</script>");
                response.setHeader("Refresh","1;vip_display");
            }
        } catch (Exception e) {
            out.print(e);
        }
    }
}
