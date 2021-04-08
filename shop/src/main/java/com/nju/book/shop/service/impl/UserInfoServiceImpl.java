package com.nju.book.shop.service.impl;

import com.nju.book.shop.mapper.UserInfoMapper;
import com.nju.book.shop.model.UserInfo;
import com.nju.book.shop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 登录
     * @param userInfo
     * @return 0:登录成功 1:用户名不对 2:密码不对
     */
    @Override
    @Cacheable(value="user",key="#userInfo.username",unless = "#result==null")
    public UserInfo selByUsername(UserInfo userInfo) {
        UserInfo qUserInfo = userInfoMapper.selectByUsername(userInfo.getUsername());
        return qUserInfo;
    }
}
