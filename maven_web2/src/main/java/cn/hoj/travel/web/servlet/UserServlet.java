package cn.hoj.travel.web.servlet;


import cn.hoj.travel.domain.ResultInfo;
import cn.hoj.travel.domain.User;
import cn.hoj.travel.service.impl.UserServiceImpl;
import cn.hoj.travel.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private   UserService service=new UserServiceImpl();
    /**
     * 用户激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activeUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //获取激活码
            String code = request.getParameter("code");
            //验证激活码
            String msg;
            if(code!=null){
//                UserService service=new UserServiceImpl();
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

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws Exception
     */
    public void registUserServlet( HttpServletRequest request,HttpServletResponse response) throws Exception {
//                request.getSession().removeAttribute("CHECKCODE_SERVER");   //清除验证码
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
        String json= null;
        try {
            json = mapper.writeValueAsString(resultInfo);
            //写回客户端
            //设置为json格式
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        System.out.println(user);
        //调用service
//        UserService service=new UserServiceImpl();
        User loginUser = service.login(user);
        System.out.println(loginUser);
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

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void exitServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {   //销毁session
            request.getSession().removeAttribute("user");
            //跳转登录页面
            response.sendRedirect(request.getContextPath()+"/login.html");
        }

    /**
     *  登录之后找到登录后存入session中的User
      * @param request
     * @param response
     * @throws IOException
     */
    public void findUserServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session
        Object user = request.getSession().getAttribute("user");
        //返回客户端
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
