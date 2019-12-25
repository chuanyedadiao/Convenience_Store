package com.chuanye.ShopOwner;

import com.chuanye.Dao.AssistantDao;
import com.chuanye.VAL.Assistant;
import com.chuanye.VAL.ShopOwner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String phone = request.getParameter("phone");
        String ageTemp = request.getParameter("age");
        String user = request.getParameter("user");
        int age = Integer.parseInt(ageTemp);
        String gender = request.getParameter("gender");
        String name = request.getParameter("name");
        String worktime = request.getParameter("worktime");
        float firstSalary = 2000; //初始工资
        HttpSession session = request.getSession();
        ShopOwner shopOwner = (ShopOwner) session.getAttribute("info");

        PrintWriter out = response.getWriter();

        AssistantDao assistantDao = new AssistantDao();
        Assistant assistant = null;

        try {
            String password = user;//注册的时候密码和id一样
            String address =null;
            String wechat = null;
            assistant = assistantDao.insertAssistant(user, name, gender,phone,password,address,wechat,age,firstSalary,worktime);
        } catch (Exception e) {
            out.print(e);
        }
        if (assistant == null) {
            out.print("<script>alert(\"注册成功！\");</script>");
            session.setAttribute("info",shopOwner);
            response.setHeader("Refresh","1;assistant_display");

        } else {
            out.print("<script>alert(\"此用户已经注册！\");</script>");
            session.setAttribute("info",shopOwner);
            response.setHeader("Refresh","1;assistant_display");
        }
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
