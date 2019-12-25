package com.chuanye.Commodity;

import com.chuanye.Dao.CommodityDao;
import com.chuanye.VAL.Assistant;
import com.chuanye.VAL.Commodity;
import com.chuanye.VAL.ShopOwner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/DisplayCommodityServlet")
public class DisplayCommodityServlet extends HttpServlet {
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
                CommodityDao commodityDao = new CommodityDao();
                count = commodityDao.getCommodityCount();
                ArrayList<Commodity> coms = commodityDao.getOnePage(currentIndex, size);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("OnePageCommodity", coms);
                session.setAttribute("sumCommodityIndex", sumIndex);
                if(temp.getClass() == Assistant.class) {
                    session.setAttribute("info",temp);
                    response.sendRedirect("Commodity/Assistantmain.jsp");
                }
                else if(temp.getClass() == ShopOwner.class){
                    session.setAttribute("info",temp);
                    response.sendRedirect("Commodity/ShopOwnermain.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            CommodityDao commodityDao = new CommodityDao();
            try {
                Commodity commodity1 = commodityDao.findWithId(key);
                Commodity commodity2 = commodityDao.findWithName(key);
                Commodity commodity;
                if(commodity1 == null && commodity2==null){
                    out.print("<script>alert(\"该商品不存在\");</script>");
                    if(temp.getClass() == Assistant.class) {
                        session.setAttribute("info",temp);
                        response.setHeader("Refresh","1;Commodity/Assistantmain.jsp");
                    }
                    else if(temp.getClass() == ShopOwner.class){
                        session.setAttribute("info",temp);
                        response.setHeader("Refresh","1;Commodity/ShopOwnermain.jsp");
                    }
                }
                else {
                    if(commodity1 != null){commodity = commodity1;}
                    else{commodity = commodity2;}
                    ArrayList<Commodity> commodities = new ArrayList<>();
                    commodities.add(commodity);
                    session.setAttribute("OnePageCommodity", commodities);
                    session.setAttribute("sumCommodityIndex", 1);
                    if(temp.getClass() == Assistant.class) {
                        session.setAttribute("info",temp);
                        response.sendRedirect("Commodity/Assistantmain.jsp");
                    }
                    else if(temp.getClass() == ShopOwner.class){
                        session.setAttribute("info",temp);
                        response.sendRedirect("Commodity/ShopOwnermain.jsp");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
