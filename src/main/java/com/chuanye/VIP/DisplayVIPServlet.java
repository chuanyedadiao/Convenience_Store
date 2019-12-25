package com.chuanye.VIP;

import com.chuanye.Dao.VIPDao;
import com.chuanye.VAL.Assistant;
import com.chuanye.VAL.ShopOwner;
import com.chuanye.VAL.VIP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DisplayVIPServlet")
public class DisplayVIPServlet extends HttpServlet {
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
                VIPDao vipDao = new VIPDao();
                count = vipDao.getVIPCount();
                ArrayList<VIP> vips = vipDao.getOnePage(currentIndex, size);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("OnePageVIP", vips);
                session.setAttribute("sumVIPIndex", sumIndex);
                if(temp.getClass() == Assistant.class) {
                    session.setAttribute("info",temp);
                    response.sendRedirect("VIP/AssistantManageVip.jsp");
                }
                else if(temp.getClass() == ShopOwner.class){
                    session.setAttribute("info",temp);
                    response.sendRedirect("VIP/ShopOwnerManageVip.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            VIPDao vipDao = new VIPDao();
            try {
                VIP vip = vipDao.findWithPhone(key);
                if(vip == null){
                    out.print("<script>alert(\"该会员不存在\");</script>");
                    if(temp.getClass() == Assistant.class) {
                        session.setAttribute("info",temp);
                        response.setHeader("Refresh","1;VIP/AssistantManageVip.jsp");
                    }
                    else if(temp.getClass() == ShopOwner.class){
                        session.setAttribute("info",temp);
                        response.setHeader("Refresh","1;VIP/ShopOwnerManageVip.jsp");
                    }
                }
                else {
                    ArrayList<VIP> vips = new ArrayList<>();
                    vips.add(vip);
                    session.setAttribute("OnePageVIP", vips);
                    session.setAttribute("sumVIPIndex", 1);
                    if(temp.getClass() == Assistant.class) {
                        session.setAttribute("info",temp);
                        response.sendRedirect("VIP/AssistantManageVip.jsp");
                    }
                    else if(temp.getClass() == ShopOwner.class){
                        session.setAttribute("info",temp);
                        response.sendRedirect("VIP/ShopOwnerManageVip.jsp");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
