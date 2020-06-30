import cn.hoj.travel.dao.CategoryDao;
import cn.hoj.travel.dao.RouteDao;
import cn.hoj.travel.dao.impl.CategoryImpl;
import cn.hoj.travel.dao.impl.RouteDaoImpl;
import cn.hoj.travel.domain.Category;
import cn.hoj.travel.domain.Route;
import org.junit.Test;

import java.util.List;

public class Testt {
    @Test
    public void test1(){
        CategoryDao dao=new CategoryImpl();
        List<Category> all = dao.findAll();
        System.out.println(all);
    }

    @Test
    public void test2(){
        RouteDao dao=new RouteDaoImpl();
        List<Route> page = dao.findByPage(5, 5,5, "旅展 ");
        System.out.println(page.get(0));
    }
}
