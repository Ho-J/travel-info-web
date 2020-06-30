package cn.hoj.travel.web.servlet;

import cn.hoj.travel.domain.ResultInfo;
import cn.hoj.travel.domain.User;
import cn.hoj.travel.service.impl.UserServiceImpl;
import cn.hoj.travel.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        Map<String, String[]> data = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        System.out.println(user);
        //调用service
        UserService service=new UserServiceImpl();
        User loginUser = service.login(user);
//        System.out.println(loginUser);
        //判断账号存在，且已激活
        ResultInfo info=new ResultInfo();
        if(loginUser!=null&&loginUser.getStatus().equalsIgnoreCase("y")){
           //登录成功，跳转登录成功
//           response.sendRedirect(request.getContextPath()+"/index.html");
//            System.out.println(request.getContextPath()+"/index.html");
//            System.out.println("页面跳转");
//            return;
            HttpSession session = request.getSession();
            session.setAttribute("user",loginUser);     //存入session保存用户信息
            info.setFlag(true);
       }else{
           //登录失败，提示错误信息

            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
       }
        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
