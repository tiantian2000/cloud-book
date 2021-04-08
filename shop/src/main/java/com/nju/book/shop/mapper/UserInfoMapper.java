package com.nju.book.shop.mapper;



import com.nju.book.shop.model.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUsername(String username);
}