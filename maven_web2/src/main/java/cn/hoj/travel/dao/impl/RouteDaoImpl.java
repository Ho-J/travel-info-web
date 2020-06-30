package cn.hoj.travel.dao.impl;

import cn.hoj.travel.dao.RouteDao;
import cn.hoj.travel.domain.Route;
import cn.hoj.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询当前数据类型的条数
     * @param cid
     * @return
     */
    @Override
    public int findTotalCount(int cid,String rname) {
        String sql="select count(*) from tab_route where 1=1";// cid=? and rname like ?
        StringBuilder stringBuilder=new StringBuilder(sql);
        List list =new ArrayList();

        if(cid!=0){
            stringBuilder.append(" and cid=? ");
            list.add(cid);
        }
        if(rname!=null){
            stringBuilder.append(" and rname like ? ");
            list.add("%"+rname+"%");
        }
        sql = stringBuilder.toString();
        return template.queryForObject(sql,Integer.class,list.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int star, int pageSize,String rname) {
        String sql="select * from tab_route where 1=1  ";  //  cid=?  and limit ?,?
        StringBuilder sqlbu=new StringBuilder(sql);
        List paramate=new ArrayList();

        if(cid!=0){
            sqlbu.append(" and cid=? ");
            paramate.add(cid);
        }
        if(rname!=null){
            sqlbu.append(" and rname like ? ");
            paramate.add("%"+rname+"%");
        }

        sqlbu.append(" limit ?,? ");
        paramate.add( star);
        paramate.add(pageSize);
        sql= sqlbu.toString();
        System.out.println(sql);
        List<Route> list;
        try {
            list = template.query(sql,new BeanPropertyRowMapper<Route>(Route.class), paramate.toArray());
        }catch (Exception e){
            System.out.println(e);
            list=null;
        }
        System.out.println(list);
        return list;
    }
}
