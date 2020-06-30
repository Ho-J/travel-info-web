package cn.hoj.travel.service.impl;

import cn.hoj.travel.dao.RouteDao;
import cn.hoj.travel.dao.impl.RouteDaoImpl;
import cn.hoj.travel.domain.PageBean;
import cn.hoj.travel.domain.Route;
import cn.hoj.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao =new RouteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int pageSize, int currentPage,String rname) {
        PageBean<Route> pageBean=new PageBean<Route>();
        int totalCount = dao.findTotalCount(cid,rname);       //总记录数
        pageBean.setTotalCount(totalCount);
        int totalPage=(totalCount%pageSize==0)?totalCount/pageSize: totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

        //查询当前页数据
        int star=(currentPage-1)*pageSize;
        List<Route> byPage = dao.findByPage(cid, star, pageSize,rname);
        pageBean.setList(byPage);

        return pageBean;
    }
}
