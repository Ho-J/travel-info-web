package cn.hoj.travel.dao.impl;

import cn.hoj.travel.dao.UserDao;
import cn.hoj.travel.domain.User;
import cn.hoj.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



public class UserDaoImpl implements UserDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过username 查询是否有次用户
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        String sql="select * from tab_user where username = ?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return user;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 插入user信息
     * @param user
     * @return
     */
    @Override
    public int insertUser(User user) {
        String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)"+
                "values(?,?,?,?,?,?,?,?,?)";
        int count= template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
        return count;
    }

    /**
     * 通过唯一激活码查询用户
     * @param code
     * @return
     */
    @Override
    public User findUserByCode(String code) {

        String sql="select * from tab_user where code=?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
            return user;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }


    }

    /**
     * 修改此用户的激活状态
     * @param user
     */
    @Override
    public int updateStae(User user) {
        String sql="UPDATE TAB_USER SET STATUS=? WHERE USERNAME=?";
        int count = template.update(sql, "Y", user.getUsername());
        return count;
    }

    /**
     * 通过用户名及密码查询用户
     * @param user
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(User user) {
        String sql="select * from tab_user where username=? and password=?";
        User loginUser =null;
        try {
            loginUser= template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
        }catch (Exception e){

        }
        return loginUser;
    }
}
