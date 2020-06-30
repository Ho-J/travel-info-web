package cn.hoj.travel.web.servlet;

import cn.hoj.travel.service.impl.UserServiceImpl;
import cn.hoj.travel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class activeUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        //验证激活码
        String msg;
        if(code!=null){
            UserService service=new UserServiceImpl();
            boolean flag = service.active(code);

            if(flag){
                msg="激活成功请<a href='localhost:8088/travel/login.html'>登录</a>";

            }else {
                msg="激活失败，请重试";
            }
        }else {
            msg="激活失败，请重试";
        }

        response.getWriter().write(msg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
