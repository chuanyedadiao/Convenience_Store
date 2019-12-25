package com.chuanye.Bill;

import com.chuanye.Dao.BillDao;
import com.chuanye.VAL.Assistant;
import com.chuanye.VAL.Bill;
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

@WebServlet(name = "DisplayBillsServlet")
public class DisplayBillsServlet extends HttpServlet {
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
                BillDao billDao = new BillDao();
                count = billDao.getBillsCount();
                ArrayList<Bill> bills = billDao.getOnePage(currentIndex, size);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("OnePageBill", bills);
                session.setAttribute("sumBillIndex", sumIndex);
                if(temp.getClass() == Assistant.class) {
                    session.setAttribute("info",temp);
                    response.sendRedirect("Bill/Assistantmain.jsp");
                }
                else if(temp.getClass() == ShopOwner.class){
                    session.setAttribute("info",temp);
                    response.sendRedirect("Bill/ShopOwnermain.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            BillDao billDao = new BillDao();
            try {
                Bill bill1 = billDao.findWithId(key);
                Bill bill2 = billDao.findWithName(key);
                Bill bill;
                if(bill1 == null && bill2==null){
                    out.print("<script>alert(\"未购买该商品不存在\");</script>");
                    if(temp.getClass() == Assistant.class) {
                        session.setAttribute("info",temp);
                        response.setHeader("Refresh","1;Bill/Assistantmain.jsp");
                    }
                    else if(temp.getClass() == ShopOwner.class){
                        session.setAttribute("info",temp);
                        response.setHeader("Refresh","1;Bill/ShopOwnermain.jsp");
                    }
                }
                else {
                    if(bill1 != null){bill = bill1;}
                    else{bill = bill1;}
                    ArrayList<Bill> bills = new ArrayList<>();
                    bills.add(bill);
                    session.setAttribute("OnePageBill", bills);
                    session.setAttribute("sumBillIndex", 1);
                    if(temp.getClass() == Assistant.class) {
                        session.setAttribute("info",temp);
                        response.sendRedirect("Bill/Assistantmain.jsp");
                    }
                    else if(temp.getClass() == ShopOwner.class){
                        session.setAttribute("info",temp);
                        response.sendRedirect("Bill/ShopOwnermain.jsp");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
