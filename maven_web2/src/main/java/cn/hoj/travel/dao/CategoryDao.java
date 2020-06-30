package cn.hoj.travel.dao;

import cn.hoj.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category > findAll();
}
