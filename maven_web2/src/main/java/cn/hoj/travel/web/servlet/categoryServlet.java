package cn.hoj.travel.web.servlet;

import cn.hoj.travel.domain.Category;
import cn.hoj.travel.service.impl.CategoryServiceImpl;
import cn.hoj.travel.service.CategoryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class categoryServlet extends BaseServlet {
    private CategoryService service=new CategoryServiceImpl();
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws IOException {


        List<Category> all = service.findAll();
        //化为json 发送到页面

//        ObjectMapper objectMapper=new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        objectMapper.writeValue(response.getOutputStream(),all);
        writeValue(all,response);
    }


}
