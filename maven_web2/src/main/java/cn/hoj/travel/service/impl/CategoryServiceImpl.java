package cn.hoj.travel.service.impl;

import cn.hoj.travel.dao.CategoryDao;
import cn.hoj.travel.dao.impl.CategoryImpl;
import cn.hoj.travel.domain.Category;
import cn.hoj.travel.service.CategoryService;
import cn.hoj.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao=new CategoryImpl();
    @Override
    public List<Category> findAll() {
        //先查redis
        Jedis jedis= JedisUtil.getJedis();

        Set<String> categorys = jedis.zrange("category", 0, -1);
        //带分数的K V
        Set<Tuple> category1 = jedis.zrangeWithScores("category", 0, -1);
        List<Category> all=null;
        if(categorys==null||categorys.size()==0){
            System.out.println("数据库查询");
            all = dao.findAll();
            for (int i=0;i<all.size();i++){
                //存redis
                jedis.zadd("category",all.get(i).getCid(),all.get(i).getCname());
            }
            return all;
        }
        System.out.println("redis查询");
        all=new ArrayList<Category>();
        Category category;
        //把查出来的值放入list
        for (Tuple name:category1){
            category=new Category();
            category.setCname(name.getElement());
            category.setCid((int) name.getScore());
            all.add(category);
        }
        return all;
    }
}
