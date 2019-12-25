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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "UploadBillsServlet")
public class UploadBillsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        BillDao billDao = new BillDao();
        CommodityDao commodityDao = new CommodityDao();
        Object info = session.getAttribute("info");
        ArrayList<Bill> bills = new ArrayList<>();
        try{
            bills = billDao.UpLoadBills();
            //添加当前时间
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");//设置日期格式
            String Time = dateFormat.format(now);//格式化然后放入字符串中
            String filename = Time + ".txt";
            float last_price=0;
            File file = new File("D:\\小黄片\\软件工程\\Convenience_Store\\BillsTotal\\"+filename);
            try{
                file.createNewFile();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            try{
                String Head = "\t\t\t\t\t\t川页和善逸家莫名其妙的便利店（感觉什么都卖）";
                FileWriter fw = new FileWriter(file);
                fw.write(Head+"\n");
                fw.write("单号\t\t\t商品名\t\t\t数量\t\t\t单价" +"\t\t\t总价\n");
                fw.flush();
                for(int i=0;i<bills.size();i++){
                    Commodity commodity = commodityDao.findWithId(bills.get(i).getId());
                    int deleteCommCount = bills.get(i).getCommCount();
                    commodityDao.updateCommodityInfo(commodity.getId(),commodity.getName(),commodity.getType(),commodity.getCount()-deleteCommCount,commodity.getPrice());
                    float temp = bills.get(i).getCommPrice()/bills.get(i).getCommCount();
                    BigDecimal b  =   new  BigDecimal(temp);
                    float per_price = b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();
                    fw.write(bills.get(i).getId()+"\t\t\t"+bills.get(i).getCommName()+"\t\t\t"+bills.get(i).getCommCount()+"\t\t\t"+per_price+"\t\t\t"+bills.get(i).getCommPrice()+"\n");
                    fw.flush();
                    last_price+=bills.get(i).getCommPrice();
                }
                fw.write("\t\t\t\t\t\t\t\t\t总价："+last_price);
                fw.flush();
                billDao.clearBills();
                fw.close();
                out.print("<script>alert(\"结算成功\");</script>");
                response.setHeader("Refresh","1;commodity_display");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            out.print(e);
            e.printStackTrace();
        }
    }
}
