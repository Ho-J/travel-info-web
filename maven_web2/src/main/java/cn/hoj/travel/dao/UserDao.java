package cn.hoj.travel.dao;

import cn.hoj.travel.domain.User;

public interface UserDao {

    /**
     * 查找此username的用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    User findUserByCode(String code);

    int updateStae(User user);

    User findUserByUsernameAndPassword(User user);

}
