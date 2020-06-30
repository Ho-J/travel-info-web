package cn.hoj.travel.web.servlet;


import cn.hoj.travel.domain.ResultInfo;
import cn.hoj.travel.domain.User;
import cn.hoj.travel.service.impl.UserServiceImpl;
import cn.hoj.travel.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registUserServlet")
public class registUserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        request.getSession().removeAttribute("CHECKCODE_SERVER");   //清除验证码
        System.out.println("resquest come");



        HttpSession session=request.getSession();
        String code=(String) session.getAttribute("CHECKCODE_SERVER");

        System.out.println(request.getParameter("check")+"   "+code);
        if (!code.equalsIgnoreCase(request.getParameter("check"))){
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setErrorMsg("验证码错误");
            resultInfo.setFlag(false);
            response.setContentType("application/json;charset=utf-8");
            //数据转为json
            ObjectMapper mapper=new ObjectMapper();
            String json= mapper.writeValueAsString(resultInfo);
            response.getWriter().write(json);
            System.out.println("验证码错误");
            return;
        }

        //获取数据
        Map<String,String[]> map=request.getParameterMap();

        //封装对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
            System.out.println(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用serveice插入数据
        UserService userService=new UserServiceImpl();
        boolean flag= userService.regist(user);

        //响应结果
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setFlag(flag);

        if(flag){

            resultInfo.setData(user);
            System.out.println("registSuccess");
        }else{

            resultInfo.setErrorMsg("注册失败");
            System.out.println("registError");
        }
        //数据转为json
        ObjectMapper mapper=new ObjectMapper();
        String json= mapper.writeValueAsString(resultInfo);

        //写回客户端
        //设置为json格式
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
