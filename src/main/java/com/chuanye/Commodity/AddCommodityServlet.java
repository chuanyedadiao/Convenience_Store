package com.chuanye.Commodity;

import com.chuanye.Dao.CommodityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddCommodityServlet")
public class AddCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Object temp = session.getAttribute("info");

        PrintWriter out = response.getWriter();

        CommodityDao commodityDao = new CommodityDao();


        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        int count = Integer.valueOf(request.getParameter("count"));
        float price = Float.parseFloat(request.getParameter("price"));

        try {
            boolean flag = commodityDao.insertCommodity(id, name, type, count, price);
            if (flag){
                response.sendRedirect("commodity_display");
            }
            else{
                out.print("<script>alert(\"该商品已存在\");</script>");
                response.setHeader("Refresh","1;commodity_display");
            }
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
