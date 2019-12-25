package com.chuanye.Assistant;

import com.chuanye.Dao.AssistantDao;
import com.chuanye.VAL.Assistant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateAssistantPasswordServlet")
public class UpdateAssistantPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        AssistantDao assistantDao = new AssistantDao();

        String id = request.getParameter("id");
        String pre_password = request.getParameter("pre_password");
        String password = request.getParameter("password");
        out.print("<h1>reach here</h1>");

        try {
            Assistant assistant = assistantDao.updateAssistantPassword(id,password,pre_password);
            if(assistant == null){
                out.print("<script>alert(\"原始密码有误\");window.location.href='Assistant/resetPassword.jsp';</script>");
            }
            else{
                out.print("<script>alert(\"修改成功\");window.location.href='login.jsp';</script>");
            }

        }
        catch (Exception e){
            out.print(e);
        }
    }
}
