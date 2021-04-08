package com.nju.book.shop.controller;


import com.nju.book.shop.model.UserInfo;
import com.nju.book.shop.service.UserInfoService;
import com.nju.book.shop.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("login")
    public ResultVO login(@RequestBody UserInfo userInfo){
        Integer flag = 0;
        UserInfo qUserInfo = userInfoService.selByUsername(userInfo);
        if(qUserInfo == null){ //没有用户
            flag = 1;
        }else{
            if(!qUserInfo.getPassword().equals(userInfo.getPassword())){ //密码不对
                flag = 2;
            }
        }
        ResultVO resultVO = new ResultVO(flag,qUserInfo);
        return resultVO;
    }
}
