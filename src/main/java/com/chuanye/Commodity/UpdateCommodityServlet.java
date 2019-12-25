package com.chuanye.Commodity;

import com.chuanye.Dao.CommodityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateCommodityServlet")
public class UpdateCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        CommodityDao commodityDao = new CommodityDao();

        String id = request.getParameter("commno");
        String name = request.getParameter("commname");
        String type = request.getParameter("commtype");
        String countTemp = request.getParameter("commcount");
        String priceTemp = request.getParameter("commprice");
        int count = Integer.parseInt(countTemp);
        float price = Float.parseFloat(priceTemp);
        try {
            commodityDao.updateCommodityInfo(id,name,type,count,price);
            response.sendRedirect("commodity_display");
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
