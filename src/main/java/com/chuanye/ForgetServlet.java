package com.chuanye;

import com.chuanye.Dao.AssistantDao;
import com.chuanye.Dao.ShopOwnerDao;
import com.chuanye.VAL.Assistant;
import com.chuanye.VAL.ShopOwner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ForgetServlet")
public class ForgetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        AssistantDao assistantDao = new AssistantDao();
        Assistant assistant = null;
        ShopOwnerDao shopOwnerDao = new ShopOwnerDao();
        ShopOwner shopOwner = new ShopOwner();
        try {
            assistant = assistantDao.findWithId(user);
            shopOwner = shopOwnerDao.findWithId(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(assistant != null)
        {
            String password = user;
            try {
            // 判断用户身份
            assistantDao.resetAssistantPassword(user,password);
            }
            catch (Exception e) {
                out.print(e);
            }
            out.print("<script>alert(\"已重置密码，请返回登录\");</script>");
            response.setHeader("Refresh","1;login.jsp");
        }
        else if(shopOwner!=null) {
            String password = user;
            try {
                shopOwnerDao.updateShopOwnerPassword(user,password);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            out.print("<script>alert(\"已重置密码，请返回登录\");</script>");
            response.setHeader("Refresh","1;login.jsp");
        }
        else {
                out.print("<script>alert(\"该Assistant不存在！请重新输入user\");</script>");
                response.setHeader("Refresh","1;forget.jsp");
        }
    }
}
