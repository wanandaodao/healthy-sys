package com.wanandaodao.bigevent.service;

import com.wanandaodao.bigevent.pojo.User;

public interface UserService {
    User selectByUsername(String username);

    void register(String username, String password);

    User getUserInfoByUsername(String username);

    void update(User user);

    void updateAvatar(String avatarUrl, int id);

    void updatePassword(User user);
}
