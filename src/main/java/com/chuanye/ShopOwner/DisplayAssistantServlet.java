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
import java.util.ArrayList;

@WebServlet("/DisplayAssistantServlet")
public class DisplayAssistantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String key = request.getParameter("key");
        Object temp = session.getAttribute("info");

        if (key == null || key=="") {
            int currentIndex, count, size = 10;
            String index = request.getParameter("index");
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);
            try {
                AssistantDao assistantDao = new AssistantDao();
                count = assistantDao.getAssistantCount();
                ArrayList<Assistant> assistants = assistantDao.getOnePage(currentIndex, size);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("OnePageAssistant", assistants);
                session.setAttribute("sumAssistantIndex", sumIndex);
                session.setAttribute("info",temp);
                response.sendRedirect("ShopOwner/displayAssistant.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            AssistantDao assistantDao = new AssistantDao();
            try {
                Assistant assistant = assistantDao.findWithId(key);
                if(assistant == null){
                    out.print("<script>alert(\"该店员不存在\");location.href = \"ShopOwner/displayAssistant.jsp\";</script>");
                }
                else {
                    ArrayList<Assistant> assistants = new ArrayList<>();
                    assistants.add(assistant);
                    session.setAttribute("OnePageAssistant", assistants);
                    session.setAttribute("sumAssistantIndex", 1);
                    session.setAttribute("info",temp);
                    response.sendRedirect("Commodity/Assistantmain.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
