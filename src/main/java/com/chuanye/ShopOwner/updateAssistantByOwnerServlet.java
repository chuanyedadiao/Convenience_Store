package com.chuanye.ShopOwner;

import com.chuanye.Dao.AssistantDao;
import com.chuanye.VAL.Assistant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateAssistantByOwnerServlet")
public class updateAssistantByOwnerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        AssistantDao assistantDao = new AssistantDao();

        String id = request.getParameter("astno");
        String name = request.getParameter("astname");
        String gender = request.getParameter("astgender");
        String phone = request.getParameter("astphone");
        String address = request.getParameter("astaddress");
        String wechat = request.getParameter("astwechat");
        int age =Integer.parseInt(request.getParameter("astage"));
        float salary =Float.parseFloat(request.getParameter("astsalary"));
        String worktime = request.getParameter("astworktime");
        try {
            Assistant assistant = assistantDao.updateAssistantByOwner(id,name,gender,phone,address,wechat,age,salary,worktime);
            response.sendRedirect("assistant_display");
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
