package cn.hoj.travel.service.impl;

import cn.hoj.travel.dao.impl.UserDaoImpl;
import cn.hoj.travel.dao.UserDao;
import cn.hoj.travel.domain.User;
import cn.hoj.travel.service.UserService;
import cn.hoj.travel.util.MailUtils;
import cn.hoj.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    UserDao dao=new UserDaoImpl();

    @Override
    public User login(User user) {

        return  dao.findUserByUsernameAndPassword(user);
    }

    @Override
    public boolean regist(User user) {
//        if(dao.get)
        User u=new User();
        u=dao.findUserByUsername(user.getUsername());
        if(u==null){
            System.out.println("可以注册");

//            System.out.println(user);
            //无此用户，注册
            //设置激活码，唯一字符串
            user.setCode(UuidUtil.getUuid());
            //设置账号状态
            user.setStatus("N");
            dao.insertUser(user);

            //发送邮箱
            String content="<a href='http://localhost:8088/travel/user/activeUserServlet?code="+user.getCode()+"'>点击激活【旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮箱");
            return true;
        }else {

        }
        return false;
    }

    @Override
    public boolean active(String code) {
        //查询激活码
        User userByCode = dao.findUserByCode(code);
        if(userByCode!=null){
            //修改 state
            if(dao.updateStae(userByCode)>0){
                return true;
            }
        }
        return false;
    }
}
