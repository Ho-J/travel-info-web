package cn.hoj.travel.service;

import cn.hoj.travel.domain.User;

public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
