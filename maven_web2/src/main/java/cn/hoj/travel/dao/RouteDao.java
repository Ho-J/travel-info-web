package cn.hoj.travel.dao;

import cn.hoj.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    //查询总记录数
    public int findTotalCount(int cid,String rname);



    /**
     * 查询当前页数据集合
     * @param cid   数据类型
     * @param star   开始查询的条数  exp： star=5  从第6条开始直到  第star+pageSize为止
     * @param pageSize  要显示的数据量
     * @return
     */
    public List<Route> findByPage(int cid,int star,int pageSize,String rname);
}
