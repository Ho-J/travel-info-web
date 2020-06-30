package cn.hoj.travel.service;

import cn.hoj.travel.domain.PageBean;
import cn.hoj.travel.domain.Route;

public interface RouteService {
    PageBean<Route> pageQuery(int cid,int pageSize,int currentPage,String rname);
}
