package cn.hoj.travel.test;


import cn.hoj.travel.dao.RouteDao;
import cn.hoj.travel.dao.impl.RouteDaoImpl;
import cn.hoj.travel.dao.impl.UserDaoImpl;
import cn.hoj.travel.dao.UserDao;
import cn.hoj.travel.domain.Route;
import cn.hoj.travel.domain.User;
import cn.hoj.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class Test {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());


    @org.junit.Test
    public void test1(){
        UserDao dao=new UserDaoImpl();
//        dao.findUserByUsername("aa");

        String sql="select * from tab_user where username=? and password=?";
        User loginUser =null;
        try {
//            loginUser= template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
            loginUser= template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), "1327193011","qweqweqwe");
        }catch (Exception e){

        }
        System.out.println();
        System.out.println(loginUser);
    }

    @org.junit.Test
    public void test2(){

    }
}
