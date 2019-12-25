package com.chuanye.Bill;

import com.chuanye.Dao.BillDao;
import com.chuanye.Dao.CommodityDao;
import com.chuanye.VAL.Commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BillUpdateServlet")
public class BillUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        BillDao billDao = new BillDao();
        HttpSession session = request.getSession();
        String id = request.getParameter("finalID");
        String name = request.getParameter("finalName");
        String countTemp = request.getParameter("finalCount");
        String priceTemp = request.getParameter("finalPrice");
        int count = Integer.parseInt(countTemp);
        float price = Float.parseFloat(priceTemp) *count;
        CommodityDao commodityDao = new CommodityDao();
        try {
            Commodity commodity = commodityDao.findWithId(id);
            if(count==0){
                billDao.deleteBill(id);
            }
            else if(count>commodity.getCount()){
                out.print("<script>alert(\"购买数量超过该商品库存\");</script>");
                response.setHeader("Refresh","1;showBills");
            }
            else{
                billDao.updateBillInfo(id,name,count,price);
                response.sendRedirect("showBills");
            }
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
