package com.chuanye.Assistant;

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

@WebServlet(name = "UpdateAssistantServlet")
public class UpdateAssistantServlet extends HttpServlet {
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

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String wechat = request.getParameter("wechat");
        int age =Integer.parseInt(request.getParameter("age"));
        try {
            Assistant assistant = assistantDao.updateAssistant(id,name,gender,phone,address,wechat,age);
            session.setAttribute("info",assistant);
            out.print("<script>alert(\"修改成功！\");location.href = \"Assistant/main.jsp\";</script>");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
