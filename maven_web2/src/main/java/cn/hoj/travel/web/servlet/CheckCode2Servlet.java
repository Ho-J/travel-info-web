package cn.hoj.travel.web.servlet;

import cn.hoj.travel.domain.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CheckCode2Servlet")
public class CheckCode2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("验证请求");
        // 验证验证码，如果验证不通过返回 false 前端使sbmit无效
        //取session
        HttpSession session=request.getSession();
        String code=(String) session.getAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo=new ResultInfo();
        System.out.println(request.getParameter("verifycode")+"   "+code);
        if (!code.equalsIgnoreCase(request.getParameter("verifycode"))){
            resultInfo.setErrorMsg("验证码错误");
            resultInfo.setFlag(false);

//            System.out.println("验证不通过");

        }else {
            resultInfo.setFlag(true);
//            System.out.println("验证通过");
        }
        response.setContentType("application/json;charset=utf-8");
        //数据转为json
        ObjectMapper mapper=new ObjectMapper();
        String json= mapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
