package com.zengs.gmall.gmallorderweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zengs.gmall.bean.UserAddress;
import com.zengs.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Reference
    UserInfoService userInfoService;

    @RequestMapping("trade")
    @ResponseBody
    public List<UserAddress> trade(String userId){
        return userInfoService.getUserAddressByUserId(userId);
    }

    @RequestMapping("tradez")
    @ResponseBody
    public List<UserAddress> trade(UserAddress userAddress){
        return userInfoService.getUserAddressByUserId(userAddress);
    }
}
