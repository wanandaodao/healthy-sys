package com.wanandaodao.bigevent.service.impl;

import com.wanandaodao.bigevent.mapper.UserMapper;
import com.wanandaodao.bigevent.pojo.User;
import com.wanandaodao.bigevent.service.UserService;
import com.wanandaodao.bigevent.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        password = Md5Util.getMD5String(password);
        userMapper.register(username,password);
    }

    @Override
    public User getUserInfoByUsername(String username) {
        return userMapper.getUserInfoByUsername(username);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl, int id) {
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
}
