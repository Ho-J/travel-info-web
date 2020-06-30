package cn.hoj.travel.domain;

import java.util.List;

//分页类
public class PageBean<T> {
    private int TotalPage;      //总页数
    private int TotalCount;     //总记录数
    private int PageSize;       //每页显示的记录数
    private int CurrentPage;    //当前页数
    private List<T> list;       //每页的数据

    @Override
    public String toString() {
        return "PageBean{" +
                "TotalPage=" + TotalPage +
                ", TotalCount=" + TotalCount +
                ", PageSize=" + PageSize +
                ", CurrentPage=" + CurrentPage +
                ", list=" + list +
                '}';
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(int totalPage) {
        TotalPage = totalPage;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int currentPage) {
        CurrentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
