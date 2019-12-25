package com.chuanye.Bill;

import com.chuanye.Dao.BillDao;
import com.chuanye.Dao.CommodityDao;
import com.chuanye.VAL.Bill;
import com.chuanye.VAL.Commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SendBuyCommInforServlet")
public class SendBuyCommInforServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        BillDao billDao = new BillDao();
        String id = request.getParameter("buyno");
        Object info = session.getAttribute("info");
        CommodityDao commodityDao = new CommodityDao();
        try {
            Commodity commodity = commodityDao.findWithId(id);
            String name = commodity.getName();
            int buycount = Integer.parseInt(request.getParameter("buycount"));
            Bill billTemp = billDao.findWithId(id);
            int countTemp=buycount;
            if(billTemp != null){
                countTemp += billTemp.getCommCount();
            }
            if(countTemp > commodity.getCount()){
                out.print("<script>alert(\"添加数量超过该商品库存\");</script>");
                response.setHeader("Refresh","1;commodity_display");
            }
            else{
                float price = commodity.getPrice()*buycount;
                billDao.insertBill(id,name,buycount,price);
                response.sendRedirect("commodity_display");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
